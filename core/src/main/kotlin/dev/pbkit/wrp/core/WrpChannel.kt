package dev.pbkit.wrp.core

import dev.pbkit.wrp.WrpMessage
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.math.min
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import pbandk.decodeFromByteArray
import pbandk.encodeToByteArray

class WrpChannel(private val socket: WrpSocket) {
    fun listen(scope: CoroutineScope): SharedFlow<WrpMessage> {
        val messages = MutableSharedFlow<WrpMessage>()
        var isSizePhase = true // true: size phase, false: payload phase
        val queue = ArrayDeque<ByteArray>()
        val sizeBuffer = ByteBuffer.allocate(4)
        sizeBuffer.order(ByteOrder.LITTLE_ENDIAN)
        var payloadSize = 0
        var payloadBuffer = ByteBuffer.allocate(payloadSize)
        scope.launch {
            while (true) {
                if (queue.size < 1) queue.addLast(socket.read() ?: break)
                val curr = queue.removeFirst()
                if (isSizePhase) {
                    val remaining = sizeBuffer.remaining()
                    sizeBuffer.put(curr, 0, min(curr.size, remaining))
                    if (curr.size > remaining) {
                        queue.addFirst(curr.copyOfRange(remaining, curr.size))
                    }
                    if (curr.size >= remaining) {
                        payloadSize = sizeBuffer.getInt(0)
                        payloadBuffer = ByteBuffer.allocate(payloadSize)
                        sizeBuffer.clear()
                        isSizePhase = false
                    }
                } else {
                    val remaining = payloadBuffer.remaining()
                    payloadBuffer.put(curr, 0, min(curr.size, remaining))
                    if (curr.size > remaining) {
                        queue.addFirst(curr.copyOfRange(remaining, curr.size))
                    }
                    if (curr.size >= remaining) {
                        val byteArray = payloadBuffer.array()
                        payloadBuffer.clear()
                        isSizePhase = true
                        messages.emit(WrpMessage.decodeFromByteArray(byteArray))
                    }
                }
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
