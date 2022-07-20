package pbkit.wrp.example

import dev.pbkit.wrp.core.WrpRequest
import dev.pbkit.wrp.core.WrpServer
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch

interface WrpExampleService {
    suspend fun GetTextValue(req: pbkit.wrp.example.WrpExample.GetTextValueRequest): pbkit.wrp.example.WrpExample.GetTextValueResponse
    suspend fun GetSliderValue(req: pbkit.wrp.example.WrpExample.GetSliderValueRequest): ReceiveChannel<pbkit.wrp.example.WrpExample.GetSliderValueResponse>
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
                                val req = pbkit.wrp.example.WrpExample.GetTextValueRequest.parseFrom(byteArray)
                                val res = impl.GetTextValue(req).toByteArray()
                                request.sendPayload(res)
                                request.req.close()
                                break
                            }
                        }
                        "pbkit.wrp.example.WrpExampleService/GetSliderValue" -> {
                            for (byteArray in request.req) {
                                val req = pbkit.wrp.example.WrpExample.GetSliderValueRequest.parseFrom(byteArray)
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
