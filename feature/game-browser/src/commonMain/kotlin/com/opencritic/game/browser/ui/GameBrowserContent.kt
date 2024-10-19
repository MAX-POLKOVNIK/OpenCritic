package com.opencritic.game.browser.ui

import com.opencritic.game.browser.domain.GameSorting
import com.opencritic.game.browser.domain.GameTimeframe
import com.opencritic.game.browser.domain.asTextSource
import com.opencritic.games.details.ui.LoadingItem
import com.opencritic.mvvm.ActionedScreenContent
import com.opencritic.mvvm.ScreenContent
import com.opencritic.resources.images.IconResource
import com.opencritic.resources.images.Icons
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource

data class GameBrowserContent(
    val platformTitleText: TextSource,
    val platformText: PlatformItem,
    val platformsItems: List<PlatformItem>,
    val timeframeTitleText: TextSource,
    val timeframeText: TimeframeItem,
    val timeframeItems: List<TimeframeItem>,
    val sortTitleText: TextSource,
    val sortText: GameSortItem,
    val sortItems: List<GameSortItem>,
    val isNextGenVisible: Boolean,
    val nextGenTitle: TextSource,
    val isNextGenChecked: Boolean,
    val browseGameItems: List<BrowseGameItem>,
    val isLoadingItemVisible: Boolean,
    val loadingItem: LoadingItem,
    val onLoadMore: () -> Unit,
    val onSelectedSort: (GameSortItem) -> Unit,
    val onSelectedTimeframe: (TimeframeItem) -> Unit,
    val onSelectedPlatform: (PlatformItem) -> Unit,
    val onNextGenChecked: (Boolean) -> Unit,
    override val isActionVisible: Boolean,
    override val actionIconResource: IconResource,
    override val onAction: () -> Unit,
) : ActionedScreenContent

@Suppress("FunctionName")
fun GameBrowserContent_PreviewData(): GameBrowserContent =
    GameBrowserContent(
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
        isNextGenVisible = true,
        isNextGenChecked = true,
        onNextGenChecked = {},
        nextGenTitle = StringRes.str_next_get_only.asTextSource(),
        browseGameItems = emptyList(),
        isLoadingItemVisible = true,
        loadingItem = LoadingItem,
        onLoadMore = { },
        onSelectedSort = { },
        onSelectedPlatform = { },
        onSelectedTimeframe = { },
        isActionVisible = true,
        actionIconResource = Icons.calendar,
        onAction = {},
    )