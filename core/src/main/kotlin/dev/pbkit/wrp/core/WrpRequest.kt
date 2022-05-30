package dev.pbkit.wrp.core

import kotlinx.coroutines.flow.SharedFlow

private typealias Metadata = Map<String, String>

class WrpRequest(
    private val channel: WrpChannel,
    val methodName: String,
    val metadata: Metadata,
    val req: SharedFlow<ByteArray>
) {
    suspend fun sendHeader(value: Metadata) {}
    suspend fun sendPayload(value: ByteArray) {}
    suspend fun sendTrailer(value: Metadata) {}
}
