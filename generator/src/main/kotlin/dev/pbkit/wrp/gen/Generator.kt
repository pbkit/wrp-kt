package dev.pbkit.wrp.gen

import java.nio.file.Paths
import pbandk.gen.ServiceGenerator

class Generator : ServiceGenerator {
    override fun generate(service: ServiceGenerator.Service): List<ServiceGenerator.Result> {
        service.debug { "Generating code for service ${service.name}" }
        val pathName =
            if (service.file.packageName == null) service.name
            else "${service.file.packageName}.${service.name}"
        var interfaceMethods = emptyList<String>()
        var availableMethods = emptyList<String>()
        var serveFunMethodHandlers = emptyList<String>()
        service.methods.forEach { method ->
            val reqType = service.kotlinTypeMappings[method.inputType!!]!!
            val respType = service.kotlinTypeMappings[method.outputType!!]!!
            interfaceMethods += if (!method.inputStreaming && !method.outputStreaming) {
                "suspend fun ${method.name}(req: $reqType): $respType"
            } else if (!method.inputStreaming && method.outputStreaming) {
                "suspend fun ${method.name}(req: $reqType): ReceiveChannel<$respType>"
            } else if (method.inputStreaming && !method.outputStreaming) {
                "suspend fun ${method.name}(req: ReceiveChannel<$reqType>): $respType"
            } else {
                "suspend fun ${method.name}(req: ReceiveChannel<$reqType>): ReceiveChannel<$respType>"
            }
            availableMethods += "\"$pathName/${method.name}\""
            serveFunMethodHandlers += (
                if (!method.inputStreaming && !method.outputStreaming) {
                    """
                    "$pathName/${method.name}" -> {
                        for (byteArray in request.req) {
                            val req = $reqType.decodeFromByteArray(byteArray)
                            val res = impl.${method.name}(req).encodeToByteArray()
                            request.sendPayload(res)
                            request.req.close()
                            break
                        }
                    }"""
                } else if (!method.inputStreaming && method.outputStreaming) {
                    """
                    "$pathName/${method.name}" -> {
                        for (byteArray in request.req) {
                            val req = $reqType.decodeFromByteArray(byteArray)
                            for (res in impl.${method.name}(req)) {
                                request.sendPayload(res.encodeToByteArray())
                            }
                            request.req.close()
                            break
                        }
                    }"""
                } else if (method.inputStreaming && !method.outputStreaming) {
                    """
                    "$pathName/${method.name}" -> {
                        val req = produce {
                            for (byteArray in request.req) {
                                val req = $reqType.decodeFromByteArray(byteArray)
                                send(req)
                            }
                        }
                        val res = impl.${method.name}(req).encodeToByteArray()
                        request.sendPayload(res)
                        req.close()
                    }"""
                } else {
                    """
                    "$pathName/${method.name}" -> {
                        val req = produce {
                            for (byteArray in request.req) {
                                val req = $reqType.decodeFromByteArray(byteArray)
                                send(req)
                            }
                        }
                        for (res in impl.${method.name}(req)) {
                            request.sendPayload(res.encodeToByteArray())
                        }
                        req.close()
                    }"""
                }
                ).trimIndent().prependIndent("                                            ")
                .substring("                                            ".length)
        }
        return listOf(
            ServiceGenerator.Result(
                otherFilePath = Paths
                    .get(service.filePath)
                    .resolveSibling(service.name + ".kt")
                    .toString(),
                code = """
                    package ${service.file.kotlinPackageName}
                    
                    import dev.pbkit.wrp.core.WrpRequest
                    import dev.pbkit.wrp.core.WrpServer
                    import kotlinx.coroutines.channels.ReceiveChannel
                    import kotlinx.coroutines.channels.produce
                    import kotlinx.coroutines.launch
                    import pbandk.decodeFromByteArray
                    import pbandk.encodeToByteArray
                    
                    interface ${service.name} {
                        ${interfaceMethods.joinToString("\n                        ")}
                    }
                    
                    fun serveWrpExampleService(impl: ${service.name}): WrpServer {
                        val availableMethods: Set<String> = setOf(
                            ${availableMethods.joinToString(",\n                            ")}
                        )
                        return object : WrpServer(availableMethods) {
                            override fun handleRequest(request: WrpRequest) {
                                request.scope.launch {
                                    request.sendHeader(mapOf())
                                    try {
                                        when (request.methodName) {
                                            ${serveFunMethodHandlers.joinToString("\n                                            ")}
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
                    """.trimIndent()
            )
        )
    }
}
