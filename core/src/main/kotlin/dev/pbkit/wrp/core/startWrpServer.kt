package dev.pbkit.wrp.core

import dev.pbkit.wrp.WrpGuestMessageReqFinish
import dev.pbkit.wrp.WrpGuestMessageReqPayload
import dev.pbkit.wrp.WrpGuestMessageReqStart
import dev.pbkit.wrp.WrpHostMessageResFinish
import dev.pbkit.wrp.WrpHostMessageResPayload
import dev.pbkit.wrp.WrpHostMessageResStart
import dev.pbkit.wrp.WrpMessage
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import pbandk.ByteArr

typealias Metadata = Map<String, String>

suspend fun startWrpServer(channel: WrpChannel, vararg servers: WrpServer) {
    startWrpServer(channel, {}, {}, *servers)
}

suspend fun startWrpServer(
    channel: WrpChannel,
    onGuestIsReady: suspend (guest: WrpGuest) -> Unit,
    vararg servers: WrpServer
) {
    startWrpServer(channel, onGuestIsReady, {}, *servers)
}

suspend fun startWrpServer(
    channel: WrpChannel,
    onGuestIsReady: suspend (guest: WrpGuest) -> Unit,
    onHostError: (hostError: WrpError) -> Unit,
    vararg servers: WrpServer
) {
    val availableMethods = servers
        .map { server -> server.availableMethods }
        .reduce { acc, set -> acc.union(set) }
    val methodServerMap: MutableMap<String, WrpServer> = mutableMapOf()
    for (server in servers) {
        for (methodName in server.availableMethods) {
            methodServerMap[methodName] = server
        }
    }
    val host = WrpHost(channel, availableMethods)

    class GuestRequest(
        val onHeader: suspend (header: Metadata) -> Unit,
        val onPayload: suspend (payload: ByteArray) -> Unit,
        val onTrailer: suspend (trailer: Metadata) -> Unit
    ) {}

    class GuestState {
        var reqCounter = 0
        val requests: MutableMap<String, GuestRequest> = mutableMapOf()
    }

    val guestState = GuestState()
    coroutineScope {
        channel.listen(this)
            .onStart { this@coroutineScope.launch { host.start() } }
            .collect { message ->
                when (val msg = message.message) {
                    null -> host.receivedNull()
                    is WrpMessage.Message.GuestReqStart -> {
                        val request = host.guestReqStart(this, msg)
                        methodServerMap[request.methodName]?.handleRequest(request)
                    }
                    is WrpMessage.Message.GuestReqPayload -> host.guestReqPayload(msg)
                    is WrpMessage.Message.GuestReqFinish -> host.guestReqFinish(msg)
                    is WrpMessage.Message.GuestResFinish -> host.guestResFinish(msg)
                    is WrpMessage.Message.HostError -> onHostError(WrpError(msg.value.message))
                    is WrpMessage.Message.HostInitialize -> this.launch {
                        onGuestIsReady(object : WrpGuest {
                            override val availableMethods: Set<String> =
                                msg.value.availableMethods.toSet()

                            override suspend fun request(
                                methodName: String,
                                req: Channel<ByteArray>,
                                metadata: Metadata,
                                onHeader: suspend (header: Metadata) -> Unit,
                                onPayload: suspend (payload: ByteArray) -> Unit,
                                onTrailer: suspend (trailer: Metadata) -> Unit
                            ) {
                                val reqId = (guestState.reqCounter++).toString()
                                guestState.requests[reqId] =
                                    GuestRequest(onHeader, onPayload, onTrailer)
                                channel.send(
                                    WrpMessage(
                                        WrpMessage.Message.GuestReqStart(
                                            WrpGuestMessageReqStart(reqId, methodName, metadata)
                                        )
                                    )
                                )
                                for (byteArray in req) {
                                    channel.send(
                                        WrpMessage(
                                            WrpMessage.Message.GuestReqPayload(
                                                WrpGuestMessageReqPayload(reqId, ByteArr(byteArray))
                                            )
                                        )
                                    )
                                }
                                channel.send(
                                    WrpMessage(
                                        WrpMessage.Message.GuestReqFinish(
                                            WrpGuestMessageReqFinish(reqId)
                                        )
                                    )
                                )
                            }
                        })
                    }
                    is WrpMessage.Message.HostResStart -> {
                        val (reqId, header) = msg.value
                        val request = guestState.requests[reqId] ?: return@collect
                        request.onHeader(header)
                    }
                    is WrpMessage.Message.HostResPayload -> {
                        val (reqId, value) = msg.value
                        val request = guestState.requests[reqId] ?: return@collect
                        request.onPayload(value.array)
                    }
                    is WrpMessage.Message.HostResFinish -> {
                        val (reqId, trailer) = msg.value
                        val request = guestState.requests[reqId] ?: return@collect
                        guestState.requests.remove(reqId)
                        request.onTrailer(trailer)
                        if (trailer["wrp-status"] != "ok") throw WrpError(trailer["wrp-message"] ?: "")
                    }
                }
            }
    }
}

class WrpError(override val message: String) : Error(message) {}

abstract class WrpServer(val availableMethods: Set<String>) {
    abstract fun handleRequest(request: WrpRequest): Unit
}

interface WrpGuest {
    val availableMethods: Set<String>
    suspend fun request(
        methodName: String,
        req: Channel<ByteArray>,
        metadata: Metadata,
        onHeader: suspend (header: Metadata) -> Unit,
        onPayload: suspend (payload: ByteArray) -> Unit,
        onTrailer: suspend (trailer: Metadata) -> Unit
    ): Unit
}

class WrpRequest(
    private val channel: WrpChannel,
    val scope: CoroutineScope,
    val methodName: String,
    val metadata: Metadata,
    val reqId: String,
    val req: Channel<ByteArray>
) {
    suspend fun sendHeader(value: Metadata) {
        channel.send(
            WrpMessage(
                WrpMessage.Message.HostResStart(
                    WrpHostMessageResStart(
                        reqId,
                        header = value
                    )
                )
            )
        )
    }

    suspend fun sendPayload(value: ByteArray) {
        channel.send(
            WrpMessage(
                WrpMessage.Message.HostResPayload(
                    WrpHostMessageResPayload(
                        reqId,
                        payload = ByteArr(value)
                    )
                )
            )
        )
    }

    suspend fun sendTrailer(value: Metadata) {
        val trailer = value.toMutableMap()
        if (!trailer.containsKey("wrp-status")) trailer["wrp-status"] = "ok"
        if (!trailer.containsKey("wrp-message")) trailer["wrp-message"] = ""
        channel.send(
            WrpMessage(
                WrpMessage.Message.HostResFinish(
                    WrpHostMessageResFinish(
                        reqId,
                        trailer = value
                    )
                )
            )
        )
    }
}
