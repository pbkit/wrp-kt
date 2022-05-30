package dev.pbkit.wrp.core

import dev.pbkit.wrp.WrpHostMessageError
import dev.pbkit.wrp.WrpHostMessageInitialize
import dev.pbkit.wrp.WrpMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

class WrpHost(private val channel: WrpChannel, private val availableMethods: Set<String>) {
    fun listen(scope: CoroutineScope): Flow<WrpRequest> {
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
                    is WrpMessage.Message.GuestReqStart -> TODO()
                    is WrpMessage.Message.GuestReqPayload -> TODO()
                    is WrpMessage.Message.GuestReqFinish -> TODO()
                    is WrpMessage.Message.GuestResFinish -> TODO()
                    else -> null
                }
            }
    }
}
