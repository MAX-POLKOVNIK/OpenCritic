package com.opencritic.game.browser.ui.period

import com.opencritic.game.browser.ui.BrowseGameItem
import com.opencritic.games.details.ui.LoadingItem
import com.opencritic.mvvm.ViewModelState

interface PeriodGameBrowserState : ViewModelState {
    data class Error(val message: String) : PeriodGameBrowserState
    data object Loading : PeriodGameBrowserState
    data class Content(
        val titleText: String,
        val browseGameItems: List<BrowseGameItem>,
        val isLoadingItemVisible: Boolean,
        val loadingItem: LoadingItem,
        val onLoadMore: () -> Unit,
    ) : PeriodGameBrowserState
}

@Suppress("FunctionName")
fun PeriodGameBrowserStateContent_PreviewData(): PeriodGameBrowserState.Content =
    PeriodGameBrowserState.Content(
        titleText = "Reviewed this week",
        browseGameItems = emptyList(),
        isLoadingItemVisible = true,
        loadingItem = LoadingItem,
        onLoadMore = {},
    )