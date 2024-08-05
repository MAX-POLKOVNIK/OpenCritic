package com.opencritic.news.ui

import com.opencritic.games.details.ui.LoadingItem

data class ArticleListContent(
    val items: List<ArticleListItem>,
    val isLoadingItemVisible: Boolean,
    val loadingItem: LoadingItem,
    val onLoadMore: () -> Unit,
)

@Suppress("FunctionName")
fun ArticleListContent_PreviewData(): ArticleListContent =
    ArticleListContent(
        items = listOf(ArticleListItem_PreviewData()),
        isLoadingItemVisible = true,
        loadingItem = LoadingItem,
        onLoadMore = {},
    )