package dev.pbkit.wrp.example

import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.pbkit.wrp.android.compose.WrpWebView
import dev.pbkit.wrp.core.WrpChannel
import dev.pbkit.wrp.core.startWrpServer
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import pbkit.wrp.example.WrpExample.GetSliderValueRequest
import pbkit.wrp.example.WrpExample.GetSliderValueResponse
import pbkit.wrp.example.WrpExample.GetTextValueRequest
import pbkit.wrp.example.WrpExample.GetTextValueResponse
import pbkit.wrp.example.WrpExampleService
import pbkit.wrp.example.WrpWrpExampleService
import pbkit.wrp.example.getSliderValueResponse
import pbkit.wrp.example.getTextValueResponse
import pbkit.wrp.example.serveWrpWrpExampleService

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
    val sliderResp = remember { mutableStateOf(0) }
    val textResp = remember { mutableStateOf("") }
    val inputText = remember { mutableStateOf(TextFieldValue("Hello, World!")) }
    val client = remember { mutableStateOf<WrpWrpExampleService?>(null) }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "WrpExampleServer (Host)",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
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
        Text(
            text = "WrpExampleClient (Guest)",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
        Row(
            Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "TextValue", fontSize = 20.sp)
                Text(text = textResp.value, fontSize = 20.sp)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "SliderValue", fontSize = 20.sp)
                Text(text = sliderResp.value.toString(), fontSize = 20.sp)
            }
        }
        Button(onClick = {
            scope.launch {
                textResp.value = client.value?.GetTextValue(GetTextValueRequest.getDefaultInstance())?.text ?: ""
            }
        }, shape = RectangleShape) {
            Text(text = "Get TextValue from Server (from WebView)")
        }
        Button(onClick = {
             scope.launch {
                 client.value?.GetSliderValue(GetSliderValueRequest.getDefaultInstance())?.consumeEach { sliderResp.value = it.value }
             }
        }, shape = RectangleShape) {
            Text(text = "Get SliderValue from Server (from WebView)")
        }
        WrpWebView(
            scope = scope,
            update = { webView ->
                WebView.setWebContentsDebuggingEnabled(true)
                webView.loadUrl("https://pbkit.dev/wrp-example")
            },
            onSocketIsReady = { _, socket, url ->
                Log.d("Wrp", "Wrp socket is ready: $url")
                startWrpServer(
                    WrpChannel(socket),
                    { wrpGuest ->
                        client.value = WrpWrpExampleService(wrpGuest)
                        Log.d("Wrp", "Wrp client is ready")
                    },
                    serveWrpWrpExampleService(object : WrpExampleService {
                        override suspend fun GetSliderValue(req: GetSliderValueRequest): ReceiveChannel<GetSliderValueResponse> {
                            val channel = Channel<GetSliderValueResponse>()
                            slider
                                .onEach {
                                    channel.send(getSliderValueResponse { value = it.toInt() })
                                }
                                .launchIn(scope)
                            return channel
                        }

                        override suspend fun GetTextValue(req: GetTextValueRequest): GetTextValueResponse {
                            return getTextValueResponse { text = inputText.value.text }
                        }
                    })
                )
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}
