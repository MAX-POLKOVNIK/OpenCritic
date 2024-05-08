package com.opencritic.game.browser.ui.period

import com.opencritic.game.browser.ui.BrowseGameItem
import com.opencritic.games.details.ui.LoadingItem
import com.opencritic.mvvm.BaseErrorState
import com.opencritic.mvvm.BaseLoadingState
import com.opencritic.mvvm.ViewModelState

interface PeriodGameBrowserState : ViewModelState {
    val titleText: String
    data class Error(
        override val titleText: String,
        override val message: String,
    ) : BaseErrorState(message), PeriodGameBrowserState

    data class Loading(
        override val titleText: String,
    ) : BaseLoadingState(), PeriodGameBrowserState

    data class Content(
        override val titleText: String,
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