package com.opencritic.news.ui

import com.opencritic.mvvm.ScreenContent
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class ArticleListContent(
    val itemIndices: ImmutableList<Int>,
    val isRefreshing: Boolean,
    val isLoadingItemVisible: Boolean,
    val onRefresh: suspend () -> Unit,
    val onRefreshRequested: () -> Unit,
    val getItemAt: (Int) -> ArticleListItem?,
) : ScreenContent

@Suppress("FunctionName")
fun ArticleListContent_PreviewData(): ArticleListContent =
    ArticleListContent(
        itemIndices = persistentListOf(),
        isRefreshing = false,
        isLoadingItemVisible = true,
        onRefresh = {},
        onRefreshRequested = {},
        getItemAt = { null }
    )