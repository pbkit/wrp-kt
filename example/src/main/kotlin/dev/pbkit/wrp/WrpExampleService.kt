package dev.pbkit.wrp

import dev.pbkit.wrp.core.WrpRequest
import dev.pbkit.wrp.core.WrpServer
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import pbandk.decodeFromByteArray
import pbandk.encodeToByteArray

interface WrpExampleService {
    suspend fun GetTextValue(req: dev.pbkit.wrp.GetTextValueRequest): dev.pbkit.wrp.GetTextValueResponse
    suspend fun GetSliderValue(req: dev.pbkit.wrp.GetSliderValueRequest): ReceiveChannel<dev.pbkit.wrp.GetSliderValueResponse>
}

fun serveWrpExampleService(impl: WrpExampleService): WrpServer {
    val availableMethods: Set<String> = setOf(
        "pbkit.wrp.example.WrpExampleService/GetTextValue",
        "pbkit.wrp.example.WrpExampleService/GetSliderValue"
    )
    return object : WrpServer(availableMethods) {
        override fun handleRequest(request: WrpRequest) {
            request.scope.launch {
                request.sendHeader(mapOf())
                try {
                    when (request.methodName) {
                        "pbkit.wrp.example.WrpExampleService/GetTextValue" -> {
                            for (byteArray in request.req) {
                                val req = dev.pbkit.wrp.GetTextValueRequest.decodeFromByteArray(byteArray)
                                val res = impl.GetTextValue(req).encodeToByteArray()
                                request.sendPayload(res)
                                request.req.close()
                                break
                            }
                        }
                        "pbkit.wrp.example.WrpExampleService/GetSliderValue" -> {
                            for (byteArray in request.req) {
                                val req = dev.pbkit.wrp.GetSliderValueRequest.decodeFromByteArray(byteArray)
                                for (res in impl.GetSliderValue(req)) {
                                    request.sendPayload(res.encodeToByteArray())
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