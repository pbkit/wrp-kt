package dev.pbkit.wrp.core

import dev.pbkit.wrp.WrpHostMessageResFinish
import dev.pbkit.wrp.WrpHostMessageResPayload
import dev.pbkit.wrp.WrpHostMessageResStart
import dev.pbkit.wrp.WrpMessage
import kotlinx.coroutines.flow.SharedFlow
import pbandk.ByteArr

private typealias Metadata = Map<String, String>

class WrpRequest(
    private val channel: WrpChannel,
    val methodName: String,
    val metadata: Metadata,
    val reqId: String,
    val req: SharedFlow<ByteArray>
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
