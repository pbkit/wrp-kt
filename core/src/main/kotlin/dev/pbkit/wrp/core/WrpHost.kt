package dev.pbkit.wrp.core

import dev.pbkit.wrp.WrpHostMessageError
import dev.pbkit.wrp.WrpHostMessageInitialize
import dev.pbkit.wrp.WrpMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onCompletion
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
    fun listen(scope: CoroutineScope): Flow<WrpRequest> {
        val states: RequestHandlingStateTable = mutableMapOf()
        fun tryForgetState(state: RequestHandlingState) {
            if (!state.reqFinished) return;
            if (!state.resFinished) return;
            states.remove(state.reqId);
        }
        return channel.listen(scope)
            .onStart {
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
            .mapNotNull { message ->
                when (val msg = message.message) {
                    null -> {
                        channel.send(
                            WrpMessage(
                                WrpMessage.Message.HostError(
                                    WrpHostMessageError(
                                        "Received null message"
                                    )
                                )
                            )
                        )
                        null
                    }
                    is WrpMessage.Message.GuestReqStart -> {
                        val (reqId, methodName, metadata) = msg.value
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
                        request
                    }
                    is WrpMessage.Message.GuestReqPayload -> {
                        val (reqId, payload) = msg.value
                        processMessage(channel, states, reqId) { state ->
                            if (!state.req.isClosedForSend) {
                                state.req.send(payload.array)
                            }
                        }
                        null
                    }
                    is WrpMessage.Message.GuestReqFinish -> {
                        val (reqId) = msg.value
                        processMessage(channel, states, reqId) { state ->
                            state.reqFinished = true
                            tryForgetState(state)
                        }
                        null
                    }
                    is WrpMessage.Message.GuestResFinish -> {
                        val (reqId) = msg.value
                        processMessage(channel, states, reqId) { state ->
                            state.resFinished = true
                            tryForgetState(state)
                        }
                        null
                    }
                    else -> null
                }
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
