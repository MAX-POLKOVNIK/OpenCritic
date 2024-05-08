package com.opencritic.game.browser.ui

import com.opencritic.game.browser.domain.GameSorting
import com.opencritic.game.browser.domain.GameTimeframe
import com.opencritic.games.details.ui.LoadingItem
import com.opencritic.mvvm.BaseErrorState
import com.opencritic.mvvm.BaseLoadingState
import com.opencritic.mvvm.ViewModelState

interface GameBrowserState : ViewModelState {
    data class Error(override val message: String) : BaseErrorState(message), GameBrowserState
    data object Loading : BaseLoadingState(), GameBrowserState
    data class Content(
        val platformTitleText: String,
        val platformText: PlatformItem,
        val platformsItems: List<PlatformItem>,
        val timeframeTitleText: String,
        val timeframeText: TimeframeItem,
        val timeframeItems: List<TimeframeItem>,
        val sortTitleText: String,
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
        sortTitleText = "Sort",
        sortText = GameSortItem(GameSorting.Score, "Score"),
        sortItems = GameSorting.entries
            .map { GameSortItem(it, it.name) },
        platformTitleText = "Platform",
        platformText = PlatformItem(
            key = null,
            text = "AllPlatforms"
        ),
        platformsItems = listOf(
            PlatformItem(key = null, "AllPlatforms"),
        ),
        timeframeTitleText = "Timeframe",
        timeframeText = TimeframeItem(GameTimeframe.AllTIme, "AllTime"),
        timeframeItems = GameTimeframe.entries
            .map { TimeframeItem(it, it.toString()) },
        browseGameItems = emptyList(),
        isLoadingItemVisible = true,
        loadingItem = LoadingItem,
        onLoadMore = { },
        onSelectedSort = { },
        onSelectedPlatform = { },
        onSelectedTimeframe = { }
    )