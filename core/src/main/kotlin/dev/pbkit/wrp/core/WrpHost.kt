package dev.pbkit.wrp.core

import dev.pbkit.wrp.WrpHostMessageError
import dev.pbkit.wrp.WrpHostMessageInitialize
import dev.pbkit.wrp.WrpMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onStart

private class RequestHandlingState(
    val reqId: String,
    val request: WrpRequest,
    val req: Channel<ByteArray>,
    var reqFinished: Boolean,
    var resFinished: Boolean
)
private typealias RequestHandlingStateTable = MutableMap<String, RequestHandlingState>

class WrpHost(private val channel: WrpChannel, private val availableMethods: Set<String>) {
    private val states: RequestHandlingStateTable = mutableMapOf()
    private fun tryForgetState(state: RequestHandlingState) {
        if (!state.reqFinished) return
        if (!state.resFinished) return
        states.remove(state.reqId)
    }

    suspend fun start() {
        channel.send(
            WrpMessage(
                WrpMessage.Message.HostInitialize(
                    WrpHostMessageInitialize(
                        availableMethods.toList()
                    )
                )
            )
        )
    }

    suspend fun receivedNull() {
        channel.send(
            WrpMessage(
                WrpMessage.Message.HostError(
                    WrpHostMessageError(
                        "Received null message"
                    )
                )
            )
        )
    }

    fun guestReqStart(
        scope: CoroutineScope,
        message: WrpMessage.Message.GuestReqStart
    ): WrpRequest {
        val (reqId, methodName, metadata) = message.value
        val req = Channel<ByteArray>(Channel.BUFFERED)
        val request: WrpRequest = WrpRequest(
            channel,
            scope,
            methodName,
            metadata,
            reqId,
            req
        )
        val state: RequestHandlingState = RequestHandlingState(
            reqId,
            request,
            req,
            reqFinished = false,
            resFinished = false
        )
        states[reqId] = state
        return request
    }

    suspend fun guestReqPayload(message: WrpMessage.Message.GuestReqPayload) {
        val (reqId, payload) = message.value
        processMessage(channel, states, reqId) { state ->
            if (!state.req.isClosedForSend) {
                state.req.send(payload.array)
            }
        }
    }

    suspend fun guestReqFinish(message: WrpMessage.Message.GuestReqFinish) {
        val (reqId) = message.value
        processMessage(channel, states, reqId) { state ->
            state.reqFinished = true
            tryForgetState(state)
        }
    }

    suspend fun guestResFinish(message: WrpMessage.Message.GuestResFinish) {
        val (reqId) = message.value
        processMessage(channel, states, reqId) { state ->
            state.resFinished = true
            tryForgetState(state)
        }
    }
}

private suspend fun processMessage(
    channel: WrpChannel,
    states: RequestHandlingStateTable,
    reqId: String,
    fn: suspend (request: RequestHandlingState) -> Unit
) {
    if (states.containsKey(reqId)) {
        fn(states[reqId]!!)
    } else {
        channel.send(
            WrpMessage(
                WrpMessage.Message.HostError(
                    WrpHostMessageError(
                        "Received unexpected request finish for { reqId: $reqId }"
                    )
                )
            )
        )
    }
}
