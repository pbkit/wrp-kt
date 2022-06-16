package dev.pbkit.wrp.android.compose

import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.viewinterop.AndroidView
import dev.pbkit.wrp.android.WrpWebView
import dev.pbkit.wrp.core.WrpSocket
import kotlinx.coroutines.CoroutineScope

@Composable
fun WrpWebView(
    scope: CoroutineScope = rememberCoroutineScope(),
    update: (WebView) -> Unit,
    onSocketIsReady: suspend (
        webView: WrpWebView,
        socket: WrpSocket,
        url: String
    ) -> Unit
) {
    AndroidView(
        factory = { context ->
            WrpWebView(context)
                .apply { initialize(scope, onSocketIsReady) }
        },
        update = update
    )
}
