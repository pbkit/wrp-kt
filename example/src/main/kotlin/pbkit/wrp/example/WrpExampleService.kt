package pbkit.wrp.example

import android.util.Log
import dev.pbkit.wrp.core.WrpGuest
import dev.pbkit.wrp.core.WrpRequest
import dev.pbkit.wrp.core.WrpServer
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

interface WrpExampleService {
    suspend fun GetTextValue(req: pbkit.wrp.example.WrpExample.GetTextValueRequest): pbkit.wrp.example.WrpExample.GetTextValueResponse
    suspend fun GetSliderValue(req: pbkit.wrp.example.WrpExample.GetSliderValueRequest): ReceiveChannel<pbkit.wrp.example.WrpExample.GetSliderValueResponse>
}

class WrpWrpExampleService constructor(private val wrpGuest: WrpGuest) : WrpExampleService {
    override suspend fun GetTextValue(req: WrpExample.GetTextValueRequest): WrpExample.GetTextValueResponse {
        val reqChannel = Channel<ByteArray>()
        var res: WrpExample.GetTextValueResponse? = null
        coroutineScope {
            launch {
                reqChannel.send(req.toByteArray())
                reqChannel.close()
            }
            wrpGuest.request(
                "pbkit.wrp.example.WrpExampleService/GetTextValue",
                reqChannel,
                mapOf(),
                {},
                { payload ->
                    res = WrpExample.GetTextValueResponse.parseFrom(payload)
                },
                {}
            )
        }
        return res!!
    }

    override suspend fun GetSliderValue(req: pbkit.wrp.example.WrpExample.GetSliderValueRequest): ReceiveChannel<pbkit.wrp.example.WrpExample.GetSliderValueResponse> {
        val reqChannel = Channel<ByteArray>()
        val resChannel = Channel<WrpExample.GetSliderValueResponse>()
        coroutineScope {
            launch {
                reqChannel.send(req.toByteArray())
                reqChannel.close()
            }
            launch {
                wrpGuest.request(
                    "pbkit.wrp.example.WrpExampleService/GetSliderValue",
                    reqChannel,
                    mapOf(),
                    {},
                    { payload -> resChannel.send(WrpExample.GetSliderValueResponse.parseFrom(payload)) },
                    {}
                )
            }
        }
        return resChannel
    }
}

fun serveWrpWrpExampleService(impl: WrpExampleService): WrpServer {
    val availableMethods: Set<String> = setOf(
        "pbkit.wrp.example.WrpExampleService/GetTextValue",
        "pbkit.wrp.example.WrpExampleService/GetSliderValue",
    )
    return object : WrpServer(availableMethods) {
        override fun handleRequest(request: WrpRequest) {
            request.scope.launch {
                request.sendHeader(mapOf())
                try {
                    when (request.methodName) {
                        "pbkit.wrp.example.WrpExampleService/GetTextValue" -> {
                            for (byteArray in request.req) {
                                val req =
                                    pbkit.wrp.example.WrpExample.GetTextValueRequest.parseFrom(
                                        byteArray
                                    )
                                val res = impl.GetTextValue(req).toByteArray()
                                request.sendPayload(res)
                                request.req.close()
                                break
                            }
                        }
                        "pbkit.wrp.example.WrpExampleService/GetSliderValue" -> {
                            for (byteArray in request.req) {
                                val req =
                                    pbkit.wrp.example.WrpExample.GetSliderValueRequest.parseFrom(
                                        byteArray
                                    )
                                for (res in impl.GetSliderValue(req)) {
                                    request.sendPayload(res.toByteArray())
                                }
                                request.req.close()
                                break
                            }
                        }
                    }
                    request.sendTrailer(mapOf())
                } catch (error: Exception) {
                    val trailer = mutableMapOf<String, String>()
                    trailer["wrp-status"] = "error"
                    trailer["wrp-message"] = error.message ?: ""
                    request.sendTrailer(trailer)
                }
            }
        }
    }
}
