package dev.pbkit.wrp.core

interface WrpSocket {
    suspend fun read(): ByteArray?
    suspend fun write(p: ByteArray): Boolean
}
