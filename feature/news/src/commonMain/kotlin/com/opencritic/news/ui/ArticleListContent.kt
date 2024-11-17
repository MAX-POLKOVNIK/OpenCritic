package com.opencritic.news.ui

import com.opencritic.games.details.ui.LoadingItem
import com.opencritic.mvvm.ScreenContent
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class ArticleListContent(
    val items: ImmutableList<ArticleListItem>,
    val isRefreshing: Boolean,
    val isLoadingItemVisible: Boolean,
    val loadingItem: LoadingItem,
    val onLoadMore: () -> Unit,
    val onRefresh: suspend () -> Unit,
    val onRefreshRequested: () -> Unit,
) : ScreenContent

@Suppress("FunctionName")
fun ArticleListContent_PreviewData(): ArticleListContent =
    ArticleListContent(
        items = persistentListOf(ArticleListItem_PreviewData()),
        isRefreshing = false,
        isLoadingItemVisible = true,
        loadingItem = LoadingItem,
        onLoadMore = {},
        onRefresh = {},
        onRefreshRequested = {},
    )