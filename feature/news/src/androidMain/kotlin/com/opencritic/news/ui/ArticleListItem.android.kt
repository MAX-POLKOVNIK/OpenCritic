package com.opencritic.news.ui

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

@Composable
fun ArticleListItem(
    item: ArticleListItem,
    modifier: Modifier = Modifier,
    windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass,
) {
    if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT) {
        ArticleVerticalListItem(item = item, modifier = modifier)
    } else {
        ArticleHorizontalListItem(item = item, modifier = modifier)
    }
}

@Preview
@Composable
fun ArticleListItem_Preview() {
    ArticleListItem(item = ArticleListItem_PreviewData())
}