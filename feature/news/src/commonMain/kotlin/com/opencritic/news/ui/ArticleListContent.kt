package com.opencritic.news.ui

import androidx.paging.ItemSnapshotList
import com.opencritic.mvvm.ScreenContent
import com.opencritic.news.domain.ArticlePreview

data class ArticleListContent(
    val items: ItemSnapshotList<ArticlePreview>,
    val isRefreshing: Boolean,
    val isLoadingItemVisible: Boolean,
    val onRefresh: suspend () -> Unit,
    val onRefreshRequested: () -> Unit,
    val getItemAt: (Int) -> ArticleListItem?,
) : ScreenContent

@Suppress("FunctionName")
fun ArticleListContent_PreviewData(): ArticleListContent =
    ArticleListContent(
        items = ItemSnapshotList(0, 0, emptyList()),
        isRefreshing = false,
        isLoadingItemVisible = true,
        onRefresh = {},
        onRefreshRequested = {},
        getItemAt = { null }
    )