package com.opencritic.auth.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AuthStateWebView(
    state: AuthState.WebView,
    modifier: Modifier = Modifier,
) {
    WebView(
        url = state.url,
        userAgent = state.authUserAgent.toString(),
        shouldOverrideUrlLoading = state.redirectHandler,
        modifier = modifier,
    )
}
