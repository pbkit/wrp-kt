package dev.pbkit.wrp.example

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.viewinterop.AndroidView
import dev.pbkit.wrp.GetSliderValueRequest
import dev.pbkit.wrp.GetSliderValueResponse
import dev.pbkit.wrp.GetTextValueRequest
import dev.pbkit.wrp.GetTextValueResponse
import dev.pbkit.wrp.WrpExampleService
import dev.pbkit.wrp.core.WrpChannel
import dev.pbkit.wrp.core.WrpSocket
import dev.pbkit.wrp.core.startWrpServer
import dev.pbkit.wrp.serveWrpExampleService
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Test()
        }
    }
}

@Composable
fun Test() {
    val scope = rememberCoroutineScope()
    val sliderValue = remember { mutableStateOf(50f) }
    val sliderValueFlow = remember { MutableSharedFlow<Float>() }
    val inputText = remember { mutableStateOf(TextFieldValue("Hello, World!")) }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "WrpExampleServer (Host)")
        Slider(
            value = sliderValue.value,
            valueRange = 0f..100f,
            onValueChange = {
                scope.launch {
                    sliderValue.value = it
                    sliderValueFlow.emit(it)
                }
            }
        )
        TextField(
            value = inputText.value,
            onValueChange = { inputText.value = it }
        )
        WrpWebView(
            update = { webView ->
                WebView.setWebContentsDebuggingEnabled(true)
                webView.loadUrl("https://pbkit.dev/wrp-example-guest")
            }
        ) { event ->
            val url = event.url
            Log.d("Wrp", "Socket is ready: $url")
            startWrpServer(
                scope = event.webViewScope,
                channel = WrpChannel(event.socket),
                servers = arrayOf(
                    serveWrpExampleService(object : WrpExampleService {
                        override suspend fun GetSliderValue(req: GetSliderValueRequest): ReceiveChannel<GetSliderValueResponse> {
                            val channel = Channel<GetSliderValueResponse>(Channel.BUFFERED)
                            channel.send(GetSliderValueResponse(sliderValue.value.toInt()))
                            event.webViewScope.launch {
                                sliderValueFlow.asSharedFlow().collect { value ->
                                    channel.send(GetSliderValueResponse(value.toInt()))
                                }
                            }
                            return channel
                        }

                        override suspend fun GetTextValue(req: GetTextValueRequest): GetTextValueResponse {
                            return GetTextValueResponse(inputText.value.text)
                        }
                    })
                )
            )
        }
    }
}

@Composable
fun WrpWebView(
    update: (WebView) -> Unit,
    onSocketIsReady: (SocketIsReadyEvent) -> Unit
) {
    val scope = rememberCoroutineScope()
    AndroidView(factory = {
        WebView(it).apply {
            settings.javaScriptEnabled = true
            var sessionCounter = 0
            val jsInterface = WrpJsInterface(scope)
            addJavascriptInterface(jsInterface, "<android-glue>")
            suspend fun evalJs(script: String) = suspendCoroutine<String> { cont ->
                this.evaluateJavascript(script) { result ->
                    cont.resume(result)
                }
            }

            suspend fun handshake() = suspendCoroutine<Boolean> { cont ->
                scope.launch {
                    for (i in 1..10) {
                        if (evalJs("'<glue>' in globalThis") == "true") {
                            cont.resume(true)
                            this.cancel()
                        }
                        delay(100)
                    }
                    cont.resume(false)
                }
            }
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
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    if (view == null || url == null) return
                    val currentSession = sessionCounter
                    scope.launch {
                        val scope = this
                        val handshakeResult = handshake()
                        if (!handshakeResult) scope.cancel()
                        val currentChannel = jsInterface.channel
                        onSocketIsReady(SocketIsReadyEvent(this, object : WrpSocket {
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
                        }, view, url))
                    }
                }
            }
        }
    }, update = update)
}

class SocketIsReadyEvent(
    val webViewScope: CoroutineScope,
    val socket: WrpSocket,
    val webView: WebView,
    val url: String
) {}

private class WrpJsInterface(val scope: CoroutineScope) {
    var channel = Channel<ByteArray>()

    @JavascriptInterface
    fun recv(data: String) {
        val payload = data.toByteArray(Charsets.ISO_8859_1)
        scope.launch { channel.send(payload) }
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
