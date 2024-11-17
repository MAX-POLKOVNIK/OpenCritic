package com.opencritic.game.browser.ui.period

import com.opencritic.game.browser.ui.BrowseGameItem
import com.opencritic.games.details.ui.LoadingItem
import com.opencritic.mvvm.ScreenContent
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class PeriodGameBrowserContent(
    val browseGameItems: ImmutableList<BrowseGameItem>,
    val isLoadingItemVisible: Boolean,
    val loadingItem: LoadingItem,
    val onLoadMore: () -> Unit,
) : ScreenContent

@Suppress("FunctionName")
fun PeriodGameBrowserContent_PreviewData(): PeriodGameBrowserContent =
    PeriodGameBrowserContent(
        browseGameItems = persistentListOf(),
        isLoadingItemVisible = true,
        loadingItem = LoadingItem,
        onLoadMore = {},
    )