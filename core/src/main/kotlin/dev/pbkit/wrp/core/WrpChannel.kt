package dev.pbkit.wrp.core

import dev.pbkit.wrp.WrpMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import pbandk.encodeToByteArray
import java.nio.ByteBuffer
import java.nio.ByteOrder

class WrpChannel(private val socket: WrpSocket) {
    fun listen(scope: CoroutineScope): SharedFlow<WrpMessage> {
        val messages = MutableSharedFlow<WrpMessage>()
        var isSizePhase = true // true: size phase, false: payload phase
        val sizeBuffer = ByteBuffer.allocate(4)
        sizeBuffer.order(ByteOrder.LITTLE_ENDIAN)
        var payloadSize = 0
        var payloadBuffer = ByteBuffer.allocate(payloadSize)
        scope.launch {
            while (true) {
                TODO()
//                val chunk = socket.read()
//                if (chunk == null) {
//                    cancel()
//                    continue
//                }
//                if (isSizePhase) {
//                    val remaining = sizeBuffer.remaining()
//                    if (remaining > chunk.size) {
//                        sizeBuffer.put(chunk)
//                    }
//                } else {
//                    //
//                }
//                break
            }
        }
        return messages
    }

    suspend fun send(message: WrpMessage) {
        val messageByteArray = message.encodeToByteArray()
        val byteBuffer = ByteBuffer.allocate(Int.SIZE_BYTES)
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN)
        val sizeByteArray = byteBuffer.putInt(messageByteArray.size).array()
        socket.write(sizeByteArray)
        socket.write(messageByteArray)
    }
}
