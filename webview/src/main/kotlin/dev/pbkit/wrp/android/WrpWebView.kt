package dev.pbkit.wrp.android

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.util.AttributeSet
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
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
    private var _webViewClient: WebViewClient? = null

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

        webChromeClient = WebChromeClient()
        webViewClient = object : WebViewClient() {
            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                _webViewClient?.onReceivedError(view, request, error)
            }

            override fun onReceivedHttpError(
                view: WebView?,
                request: WebResourceRequest?,
                errorResponse: WebResourceResponse?
            ) {
                super.onReceivedHttpError(view, request, errorResponse)
                _webViewClient?.onReceivedHttpError(view, request, errorResponse)
            }

            override fun onPageCommitVisible(view: WebView?, url: String?) {
                super.onPageCommitVisible(view, url)
                _webViewClient?.onPageCommitVisible(view, url)
            }

            @RequiresApi(Build.VERSION_CODES.N)
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return _webViewClient?.shouldOverrideUrlLoading(view, request)
                    ?: super.shouldOverrideUrlLoading(view, request)
            }

            @Deprecated("Deprecated in Java")
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                return _webViewClient?.shouldOverrideUrlLoading(view, url)
                    ?: super.shouldOverrideUrlLoading(view, url)
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                _webViewClient?.onPageStarted(view, url, favicon)
                jsInterface.channel.close()
                jsInterface.channel = Channel(Channel.BUFFERED)
                ++sessionCounter
                socketJob?.cancel()
                socketJob = null
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                _webViewClient?.onPageFinished(view, url)
                if (view == null || url == null) return
                val currentSession = sessionCounter
                if (socketJob == null) {
                    socketJob = scope.launch {
                        val handshakeResult = handshake()
                        if (!handshakeResult) {
                            cancel()
                            return@launch
                        }
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
    }

    override fun setWebViewClient(webViewClient: WebViewClient) {
        _webViewClient = webViewClient
    }

    suspend fun handshake() = coroutineScope {
        if (evalJs("globalThis['<android>']") == "true") return@coroutineScope false
        evalJs("globalThis['<android>'] = true")
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
                    if (code < 0) {
                        out.append(String.format("\\x%02x", 0x100 + code))
                    } else {
                        out.append(String.format("\\x%02x", code))
                    }
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
