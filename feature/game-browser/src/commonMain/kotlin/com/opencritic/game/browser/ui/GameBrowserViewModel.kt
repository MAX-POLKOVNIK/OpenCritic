package com.opencritic.game.browser.ui

import com.opencritic.calendar.api.CalendarRoute
import com.opencritic.game.browser.domain.BrowseGame
import com.opencritic.game.browser.domain.GameSorting
import com.opencritic.game.browser.domain.GameTimeframe
import com.opencritic.game.browser.domain.GetBrowseGamesInteractor
import com.opencritic.game.browser.domain.GetPlatformsInteractor
import com.opencritic.game.browser.domain.asTextSource
import com.opencritic.game.browser.domain.images
import com.opencritic.games.Platform
import com.opencritic.games.details.api.ui.GameDetailsRoute
import com.opencritic.games.details.ui.LoadingItem
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseContentViewModel
import com.opencritic.mvvm.CommonViewModelState
import com.opencritic.remote.images.ImagePreloader
import com.opencritic.resources.images.Icons
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.asTextSource
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch

class GameBrowserViewModel(
    private val getPlatformsInteractor: GetPlatformsInteractor,
    private val getBrowseGamesInteractor: GetBrowseGamesInteractor,
    private val logger: Logger,
    private val imagePreloader: ImagePreloader,
) : BaseContentViewModel<GameBrowserContent>() {
    override fun initialState(): CommonViewModelState<GameBrowserContent> =
        CommonViewModelState.loading(title = StringRes.str_tab_browse.asTextSource())

    private var platforms: List<Platform>? = null
    private var canLoadMore: Boolean = true
    private var sorting: GameSorting = GameSorting.Score
    private var timeframe: GameTimeframe = GameTimeframe.AllTIme
    private var platform: Platform? = null
    private var isNextGenVisible: Boolean = false
    private var isNextGenChecked: Boolean = true

    override fun onCleared() {
        super.onCleared()

        imagePreloader.cancel()
    }

    override fun onStateInit() {
        super.onStateInit()

        scope.launch {
            getPlatformsInteractor()
                .onFailure {
                    showError(it) {
                        onStateInit()
                    }
                }
                .onSuccess { platforms ->
                    setContent {
                        createContentState(platforms)
                    }

                    this@GameBrowserViewModel.platforms = platforms
                }

            if (platforms != null) {
                getBrowseGamesInteractor(
                    platformCode = platform?.code ?: "",
                    skip = 0,
                    sorting = sorting,
                    time = timeframe,
                    isExclusive = isNextGenVisible && isNextGenChecked
                )
                    .onFailure {
                        logger.log(it.toString())
                    }
                    .onSuccess { games ->
                        val content = requireContent()

                        imagePreloader.load(games.images)

                        updateContentIfSet {
                            content.copy(
                                browseGameItems = content.browseGameItems.mapAndAdd(games, ::navigateToGame),
                                isLoadingItemVisible = games.isNotEmpty()
                            )
                        }

                        canLoadMore = games.isNotEmpty()
                    }
            }
        }
    }

    private fun createContentState(
        platforms: List<Platform>,
    ): GameBrowserContent =
        GameBrowserContent(
            sortTitleText = StringRes.str_sort.asTextSource(),
            sortText = GameSortItem(sorting, sorting.asTextSource()),
            sortItems = GameSorting.entries
                .map { GameSortItem(it, it.asTextSource()) }
                .toImmutableList(),
            platformTitleText = StringRes.str_platform.asTextSource(),
            platformText = PlatformItem(
                key = platform,
                text = if (platform == null) StringRes.str_all_platforms.asTextSource()
                       else requireNotNull(platform).name.asTextSource()
            ),
            platformsItems = listOf(
                PlatformItem(key = null, StringRes.str_all_platforms.asTextSource()),
                *platforms.map { PlatformItem(it, it.name.asTextSource()) }.toTypedArray()
            ).toImmutableList(),
            timeframeTitleText = StringRes.str_timeframe.asTextSource(),
            timeframeText = TimeframeItem(timeframe, timeframe.asTextSource()),
            timeframeItems = GameTimeframe.entries
                .map { TimeframeItem(it, it.asTextSource()) }
                .toImmutableList(),
            isNextGenVisible = isNextGenVisible,
            isNextGenChecked = isNextGenChecked,
            nextGenTitle = StringRes.str_next_get_only.asTextSource(),
            browseGameItems = persistentListOf(),
            isLoadingItemVisible = true,
            loadingItem = LoadingItem,
            onLoadMore = ::loadMore,
            onSelectedSort = ::onSortSelected,
            onSelectedPlatform = ::onPlatformSelected,
            onSelectedTimeframe = ::onTimeframeSelected,
            onNextGenChecked = ::onNextGenChecked,
            isActionVisible = true,
            actionIconResource = Icons.calendar,
            onAction = ::navigateToCalendar,
        )

    private fun loadMore() {
        scope.launch {
            if (!canLoadMore)
                return@launch

            val content = requireContent()

            getBrowseGamesInteractor(
                platformCode = platform?.code ?: "",
                skip = content.browseGameItems.size,
                sorting = sorting,
                time = timeframe,
                isExclusive = isNextGenVisible && isNextGenChecked
            )
                .onSuccess { games ->
                    imagePreloader.load(games.images)

                    updateContentIfSet {
                        copy(
                            browseGameItems = content.browseGameItems.mapAndAdd(games, ::navigateToGame),
                            isLoadingItemVisible = games.isNotEmpty()
                        )
                    }
                }
                .onFailure {
                    logger.log(it.toString())
                }
        }
    }

    private fun onSortSelected(item: GameSortItem) {
        if (item.key == sorting)
            return

        sorting = item.key
        canLoadMore = true

        updateContentIfSet {
            copy(
                sortText = item,
                browseGameItems = persistentListOf(),
                isLoadingItemVisible = true
            )
        }

        loadMore()
    }

    private fun onNextGenChecked(isChecked: Boolean) {
        if (isNextGenChecked == isChecked)
            return

        isNextGenChecked = isChecked
        canLoadMore = true

        updateContentIfSet {
            copy(
                isNextGenChecked = isChecked,
                browseGameItems = persistentListOf(),
                isLoadingItemVisible = true
            )
        }

        loadMore()
    }

    private fun onTimeframeSelected(item: TimeframeItem) {
        if (item.key == timeframe)
            return

        timeframe = item.key
        canLoadMore = true

        updateContentIfSet {
            copy(
                timeframeText = item,
                browseGameItems = persistentListOf(),
                isLoadingItemVisible = true
            )
        }

        loadMore()
    }

    private fun onPlatformSelected(item: PlatformItem) {
        if (item.key == platform)
            return

        platform = item.key
        canLoadMore = true

        isNextGenChecked = true
        isNextGenVisible = item.key?.isNextGenAvailable == true

        updateContentIfSet {
            copy(
                isNextGenChecked = this@GameBrowserViewModel.isNextGenChecked,
                isNextGenVisible = this@GameBrowserViewModel.isNextGenVisible,
                platformText = item,
                browseGameItems = persistentListOf(),
                isLoadingItemVisible = true
            )
        }

        loadMore()
    }

    private fun navigateToGame(item: BrowseGameItem) =
        GameDetailsRoute.navigate(
            GameDetailsRoute.InitArgs(item.id, item.nameText)
        )

    private fun navigateToCalendar() {
        CalendarRoute.navigate(CalendarRoute.InitArgs)
    }
}