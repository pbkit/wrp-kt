package dev.pbkit.wrp.example

import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.TextFieldValue
import dev.pbkit.wrp.GetSliderValueRequest
import dev.pbkit.wrp.GetSliderValueResponse
import dev.pbkit.wrp.GetTextValueRequest
import dev.pbkit.wrp.GetTextValueResponse
import dev.pbkit.wrp.WrpExampleService
import dev.pbkit.wrp.android.compose.WrpWebView
import dev.pbkit.wrp.core.WrpChannel
import dev.pbkit.wrp.core.startWrpServer
import dev.pbkit.wrp.serveWrpExampleService
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

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
    val slider = remember { MutableStateFlow(50f) }
    val inputText = remember { mutableStateOf(TextFieldValue("Hello, World!")) }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "WrpExampleServer (Host)")
        Slider(
            value = slider.collectAsState().value,
            valueRange = 0f..100f,
            onValueChange = {
                slider.value = it
            }
        )
        TextField(
            value = inputText.value,
            onValueChange = { inputText.value = it }
        )
        WrpWebView(
            scope = scope,
            update = { webView ->
                WebView.setWebContentsDebuggingEnabled(true)
                webView.loadUrl("https://pbkit.dev/wrp-example-guest")
            }
        ) { _, socket, url ->
            Log.d("Wrp", "Socket is ready: $url")
            startWrpServer(
                WrpChannel(socket),
                serveWrpExampleService(object : WrpExampleService {
                    override suspend fun GetSliderValue(req: GetSliderValueRequest): ReceiveChannel<GetSliderValueResponse> {
                        val channel = Channel<GetSliderValueResponse>()
                        slider
                            .onEach { channel.send(GetSliderValueResponse(it.toInt())) }
                            .launchIn(scope)
                        return channel
                    }

                    override suspend fun GetTextValue(req: GetTextValueRequest): GetTextValueResponse {
                        return GetTextValueResponse(inputText.value.text)
                    }
                })
            )
        }
    }
}
