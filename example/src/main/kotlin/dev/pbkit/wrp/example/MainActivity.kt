package dev.pbkit.wrp.example

import android.os.Bundle
import android.util.Log
import android.webkit.JavascriptInterface
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
import dev.pbkit.wrp.core.WrpSocket
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
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
    val sliderValue = remember { mutableStateOf(50f) }
    val inputText = remember { mutableStateOf(TextFieldValue("Hello, World!")) }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "WrpExampleServer (Host)")
        Slider(
            value = sliderValue.value,
            valueRange = 0f..100f,
            onValueChange = { sliderValue.value = it.toFloat() }
        )
        TextField(
            value = inputText.value,
            onValueChange = { inputText.value = it }
        )
        WrpWebView() { socket, _, url ->
            Log.d("Wrp", "Socket is ready: $url")
            // TODO
        }
    }
}

@Composable
fun WrpWebView(onSocketIsReady: (socket: WrpSocket, webView: WebView, url: String) -> Unit) {
    val scope = rememberCoroutineScope()
    AndroidView(factory = {
        WebView(it).apply {
            val queue = ArrayDeque<ByteArray>()
            var outerCont: Continuation<ByteArray?>? = null
            settings.javaScriptEnabled = true
            addJavascriptInterface(
                object : WrpJsInterface() {
                    override fun recv(data: String) {
                        val payload = data.toByteArray(Charsets.ISO_8859_1)
                        if (outerCont == null) {
                            queue.addLast(payload)
                        } else {
                            outerCont!!.resume(payload)
                            outerCont = null
                        }
                    }
                }, "<android-glue>"
            )
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
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    if (view == null || url == null) return
                    scope.launch {
                        val handshakeResult = handshake()
                        onSocketIsReady(object : WrpSocket {
                            override suspend fun read(): ByteArray? = suspendCoroutine { cont ->
                                if (queue.size > 0) {
                                    val payload = queue.removeFirst()
                                    cont.resume(payload)
                                } else {
                                    outerCont = cont
                                }
                            }

                            override suspend fun write(p: ByteArray): Boolean {
                                val payload = ba2str(p)
                                evalJs("globalThis['<glue>'].recv($payload)")
                                return true
                            }
                        }, view, url)
                    }
                }
            }
        }
    }, update = {
        it.loadUrl("https://pbkit.dev/wrp-example-guest")
    })
}

private abstract class WrpJsInterface {
    @JavascriptInterface
    abstract fun recv(data: String)
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
