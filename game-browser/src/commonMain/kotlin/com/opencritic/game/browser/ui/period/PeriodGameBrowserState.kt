package com.opencritic.game.browser.ui.period

import com.opencritic.game.browser.ui.BrowseGameItem
import com.opencritic.games.details.ui.LoadingItem
import com.opencritic.mvvm.BaseErrorState
import com.opencritic.mvvm.BaseLoadingState
import com.opencritic.mvvm.ViewModelState
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource

interface PeriodGameBrowserState : ViewModelState {
    val titleText: TextSource
    data class Error(
        override val titleText: TextSource,
        override val message: String,
    ) : BaseErrorState(message), PeriodGameBrowserState

    data class Loading(
        override val titleText: TextSource,
    ) : BaseLoadingState(), PeriodGameBrowserState

    data class Content(
        override val titleText: TextSource,
        val browseGameItems: List<BrowseGameItem>,
        val isLoadingItemVisible: Boolean,
        val loadingItem: LoadingItem,
        val onLoadMore: () -> Unit,
    ) : PeriodGameBrowserState
}

@Suppress("FunctionName")
fun PeriodGameBrowserStateContent_PreviewData(): PeriodGameBrowserState.Content =
    PeriodGameBrowserState.Content(
        titleText = "Reviewed this week".asTextSource(),
        browseGameItems = emptyList(),
        isLoadingItemVisible = true,
        loadingItem = LoadingItem,
        onLoadMore = {},
    )