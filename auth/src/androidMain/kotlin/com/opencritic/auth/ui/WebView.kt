package com.opencritic.auth.ui

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebView(
    url: String,
    userAgent: String,
    shouldOverrideUrlLoading: (String) -> Boolean,
    modifier: Modifier = Modifier,
) {
    AndroidView(
        factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )

                CookieManager.getInstance()
                    .apply {
                        removeAllCookies {}
                        setAcceptCookie(true)
                    }
                    .setAcceptThirdPartyCookies(this, true)

                settings.apply {
                    clearCache(true)
                    javaScriptEnabled = true
                    userAgentString = userAgent
                    cacheMode = WebSettings.LOAD_DEFAULT
                    javaScriptCanOpenWindowsAutomatically = true
                    setSupportMultipleWindows(true)
                    domStorageEnabled = true
                    javaScriptCanOpenWindowsAutomatically =true
                }

                webViewClient = CustomWebViewClient(shouldOverrideUrlLoading)
                webChromeClient = WebChromeClient()
            }
        },
        update = {
            it.loadUrl(url)
        },
        modifier = modifier,
    )
}

private class CustomWebViewClient(
    private val shouldOverrideUrlLoading: (String) -> Boolean,
) : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean =
        shouldOverrideUrlLoading(request?.url?.toString() ?: "")
}