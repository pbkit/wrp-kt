package dev.pbkit.wrp.core

import kotlinx.coroutines.coroutineScope

suspend fun startWrpServer(channel: WrpChannel, vararg servers: WrpServer) {
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
    coroutineScope {
        host.listen(this).collect { request ->
            val server = methodServerMap[request.methodName] ?: return@collect
            server.handleRequest(request)
        }
    }
}

abstract class WrpServer(val availableMethods: Set<String>) {
    abstract fun handleRequest(request: WrpRequest)
}
