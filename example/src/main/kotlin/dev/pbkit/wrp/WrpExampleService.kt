package dev.pbkit.wrp

import dev.pbkit.wrp.core.WrpRequest
import dev.pbkit.wrp.core.WrpServer
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import pbandk.decodeFromByteArray
import pbandk.encodeToByteArray

interface WrpExampleService {
    suspend fun GetTextValue(req: dev.pbkit.wrp.GetTextValueRequest): dev.pbkit.wrp.GetTextValueResponse
    suspend fun GetSliderValue(req: dev.pbkit.wrp.GetSliderValueRequest): dev.pbkit.wrp.GetSliderValueResponse
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
                            request.req.take(1).collect { byteArray ->
                                val req = dev.pbkit.wrp.GetTextValueRequest.decodeFromByteArray(byteArray)
                                val res = impl.GetTextValue(req).encodeToByteArray()
                                request.sendPayload(res)
                            }
                        }
                        "pbkit.wrp.example.WrpExampleService/GetSliderValue" -> {
                            request.req.take(1).collect { byteArray ->
                                val req = dev.pbkit.wrp.GetSliderValueRequest.decodeFromByteArray(byteArray)
                                val res = impl.GetSliderValue(req).encodeToByteArray()
                                request.sendPayload(res)
                            }
                        }
                    }
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
