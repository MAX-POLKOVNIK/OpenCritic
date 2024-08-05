package com.opencritic.news.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.opencritic.games.details.ui.articleHtmlContent

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun ArticleWevView(
    html: String,
    modifier: Modifier = Modifier,
) {
    val textColor = MaterialTheme.colorScheme.contentColorFor(MaterialTheme.colorScheme.background).toArgb()
        .let { String.format("#%06X", (0xFFFFFF and it)) }

    AndroidView(
        factory = {
            WebView(it).apply {
                setBackgroundColor(Color.TRANSPARENT)
                isVerticalScrollBarEnabled = false
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                settings.apply {
                    javaScriptEnabled = true
                }
            }
        },
        update = {
            val htmlContent = articleHtmlContent(html, textColor)
            it.loadDataWithBaseURL(
                /* baseUrl = */ null,
                /* data = */ htmlContent,
                /* mimeType = */ "text/html",
                /* encoding = */ "UTF-8",
                /* historyUrl = */ null,
            )
        },
        modifier = modifier
            .defaultMinSize(minHeight = 100.dp),
    )
}