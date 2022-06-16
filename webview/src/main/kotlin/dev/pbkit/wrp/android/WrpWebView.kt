package dev.pbkit.wrp.android

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.webkit.JavascriptInterface
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import dev.pbkit.wrp.core.WrpSocket
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("SetJavaScriptEnabled")
class WrpWebView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : WebView(context, attrs, defStyleAttr) {

    var sessionCounter = 0
    var socketJob: Job? = null

    init {
        settings.javaScriptEnabled = true
    }

    fun initialize(
        scope: CoroutineScope,
        onSocketIsReady: suspend (
            webView: WrpWebView,
            socket: WrpSocket,
            url: String
        ) -> Unit
    ) {
        val jsInterface = WrpJsInterface(scope)
        addJavascriptInterface(jsInterface, "<android-glue>")

        webChromeClient = object : WebChromeClient() {
            override fun onJsAlert(
                view: WebView?,
                url: String?,
                message: String?,
                result: JsResult?
            ): Boolean {
                return super.onJsAlert(view, url, message, result)
            }
        }
        webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                jsInterface.channel.close()
                jsInterface.channel = Channel<ByteArray>(Channel.BUFFERED)
                ++sessionCounter
                socketJob?.cancel()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (view == null || url == null) return
                val currentSession = sessionCounter
                socketJob = scope.launch {
                    val handshakeResult = handshake()
                    if (!handshakeResult) cancel()
                    val currentChannel = jsInterface.channel
                    val socket = object : WrpSocket {
                        override suspend fun read(): ByteArray? {
                            if (currentSession != sessionCounter) return null
                            return try {
                                currentChannel.receive()
                            } catch (e: Exception) {
                                null
                            }
                        }

                        override suspend fun write(p: ByteArray): Boolean {
                            val payload = ba2str(p)
                            evalJs("globalThis['<glue>'].recv($payload)")
                            return true
                        }
                    }
                    onSocketIsReady(this@WrpWebView, socket, url)
                }
            }
        }
    }

    suspend fun handshake() = coroutineScope {
        for (i in 1..10) {
            if (evalJs("'<glue>' in globalThis") == "true") {
                return@coroutineScope true
            }
            delay(100)
        }
        return@coroutineScope false
    }

    suspend fun evalJs(script: String) = suspendCoroutine<String> { cont ->
        this.evaluateJavascript(script) { result ->
            cont.resume(result)
        }
    }

    private fun ba2str(ba: ByteArray): String {
        val out = StringBuilder()
        out.append('\"')
        var i = 0
        while (i < ba.size) {
            when (val code = ba[i].toInt()) {
                8 -> out.append("\\b") // \b
                9 -> out.append("\\t") // \t
                10 -> out.append("\\n") // \n
                12 -> out.append("\\f") // \f
                13 -> out.append("\\r") // \r
                34, 92 -> out.append('\\').append(code.toChar()) // ", \
                else -> if (code <= 0x1F) {
                    out.append(String.format("\\u%04x", code))
                } else {
                    out.append(code.toChar())
                }
            }
            ++i
        }
        out.append('\"')
        return out.toString()
    }

    private class WrpJsInterface(val scope: CoroutineScope) {
        var channel = Channel<ByteArray>()

        @JavascriptInterface
        fun recv(data: String) {
            val payload = data.toByteArray(Charsets.ISO_8859_1)
            scope.launch { channel.send(payload) }
        }
    }
}
