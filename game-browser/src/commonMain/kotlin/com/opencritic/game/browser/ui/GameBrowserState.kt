package com.opencritic.game.browser.ui

import com.opencritic.game.browser.domain.GameSorting
import com.opencritic.game.browser.domain.GameTimeframe
import com.opencritic.game.browser.domain.asTextSource
import com.opencritic.games.details.ui.LoadingItem
import com.opencritic.mvvm.BaseErrorState
import com.opencritic.mvvm.BaseLoadingState
import com.opencritic.mvvm.ViewModelState
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource

interface GameBrowserState : ViewModelState {
    data class Error(override val message: TextSource) : BaseErrorState(message), GameBrowserState
    data object Loading : BaseLoadingState(), GameBrowserState
    data class Content(
        val platformTitleText: TextSource,
        val platformText: PlatformItem,
        val platformsItems: List<PlatformItem>,
        val timeframeTitleText: TextSource,
        val timeframeText: TimeframeItem,
        val timeframeItems: List<TimeframeItem>,
        val sortTitleText: TextSource,
        val sortText: GameSortItem,
        val sortItems: List<GameSortItem>,
        val browseGameItems: List<BrowseGameItem>,
        val isLoadingItemVisible: Boolean,
        val loadingItem: LoadingItem,
        val onLoadMore: () -> Unit,
        val onSelectedSort: (GameSortItem) -> Unit,
        val onSelectedTimeframe: (TimeframeItem) -> Unit,
        val onSelectedPlatform: (PlatformItem) -> Unit,
    ) : GameBrowserState
}

@Suppress("FunctionName")
fun GameBrowserStateContent_PreviewData(): GameBrowserState.Content =
    GameBrowserState.Content(
        sortTitleText = "Sort".asTextSource(),
        sortText = GameSortItem(GameSorting.Score, "Score".asTextSource()),
        sortItems = GameSorting.entries
            .map { GameSortItem(it, it.asTextSource()) },
        platformTitleText = "Platform".asTextSource(),
        platformText = PlatformItem(
            key = null,
            text = "AllPlatforms".asTextSource()
        ),
        platformsItems = listOf(
            PlatformItem(key = null, "AllPlatforms".asTextSource()),
        ),
        timeframeTitleText = "Timeframe".asTextSource(),
        timeframeText = TimeframeItem(GameTimeframe.AllTIme, "AllTime".asTextSource()),
        timeframeItems = GameTimeframe.entries
            .map { TimeframeItem(it, it.asTextSource()) },
        browseGameItems = emptyList(),
        isLoadingItemVisible = true,
        loadingItem = LoadingItem,
        onLoadMore = { },
        onSelectedSort = { },
        onSelectedPlatform = { },
        onSelectedTimeframe = { }
    )