package com.opencritic.game.browser.ui

import com.opencritic.calendar.api.CalendarRoute
import com.opencritic.game.browser.domain.BrowseGame
import com.opencritic.game.browser.domain.GameSorting
import com.opencritic.game.browser.domain.GameTimeframe
import com.opencritic.game.browser.domain.GetBrowseGamesInteractor
import com.opencritic.game.browser.domain.GetPlatformsInteractor
import com.opencritic.game.browser.domain.asTextSource
import com.opencritic.games.Platform
import com.opencritic.games.details.api.ui.GameDetailsRoute
import com.opencritic.games.details.ui.LoadingItem
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseContentViewModel
import com.opencritic.mvvm.CommonViewModelState
import com.opencritic.resources.images.Icons
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.asTextSource
import kotlinx.coroutines.launch

class GameBrowserViewModel(
    private val getPlatformsInteractor: GetPlatformsInteractor,
    private val getBrowseGamesInteractor: GetBrowseGamesInteractor,
    private val logger: Logger,
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

                        updateContentIfSet {
                            content.copy(
                                browseGameItems = content.browseGameItems + games.map { game ->
                                    BrowseGameItem(game)
                                },
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
                .map { GameSortItem(it, it.asTextSource()) },
            platformTitleText = StringRes.str_platform.asTextSource(),
            platformText = PlatformItem(
                key = platform,
                text = if (platform == null) StringRes.str_all_platforms.asTextSource()
                       else requireNotNull(platform).name.asTextSource()
            ),
            platformsItems = listOf(
                PlatformItem(key = null, StringRes.str_all_platforms.asTextSource()),
                *platforms.map { PlatformItem(it, it.name.asTextSource()) }.toTypedArray()
            ),
            timeframeTitleText = StringRes.str_timeframe.asTextSource(),
            timeframeText = TimeframeItem(timeframe, timeframe.asTextSource()),
            timeframeItems = GameTimeframe.entries
                .map { TimeframeItem(it, it.asTextSource()) },
            isNextGenVisible = isNextGenVisible,
            isNextGenChecked = isNextGenChecked,
            nextGenTitle = StringRes.str_next_get_only.asTextSource(),
            browseGameItems = emptyList(),
            isLoadingItemVisible = true,
            loadingItem = LoadingItem,
            onLoadMore = { loadMore() },
            onSelectedSort = { onSortSelected(it) },
            onSelectedPlatform = { onPlatformSelected(it) },
            onSelectedTimeframe = { onTimeframeSelected(it) },
            onNextGenChecked = { onNextGenChecked(it) },
            isActionVisible = true,
            actionIconResource = Icons.calendar,
            onAction = { navigateToCalendar() },
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
                .onSuccess { reviews ->
                    logger.log("Loaded reviews count: ${reviews.size} --- $platform $sorting $timeframe ${content.browseGameItems.size}")

                    updateContentIfSet {
                        copy(
                            browseGameItems = content.browseGameItems + reviews.map { game ->
                                BrowseGameItem(game)
                            },
                            isLoadingItemVisible = reviews.isNotEmpty()
                        )
                    }
                }
                .onFailure {
                    logger.log(it.toString())
                }
        }
    }

    private fun BrowseGameItem(game: BrowseGame): BrowseGameItem =
        BrowseGameItem(
            game = game,
            isPercentRecommendedVisible = sorting == GameSorting.PercentRecommended,
            onClick = { openGame(game.id, game.name) },
        )

    private fun onSortSelected(item: GameSortItem) {
        if (item.key == sorting)
            return

        sorting = item.key
        canLoadMore = true

        updateContentIfSet {
            copy(
                sortText = item,
                browseGameItems = emptyList(),
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
                browseGameItems = emptyList(),
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
                browseGameItems = emptyList(),
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
                browseGameItems = emptyList(),
                isLoadingItemVisible = true
            )
        }

        loadMore()
    }

    private fun openGame(gameId: Long, gameName: String) {
        GameDetailsRoute.navigate(
            GameDetailsRoute.InitArgs(gameId, gameName)
        )
    }

    private fun navigateToCalendar() {
        CalendarRoute.navigate(CalendarRoute.InitArgs)
    }
}