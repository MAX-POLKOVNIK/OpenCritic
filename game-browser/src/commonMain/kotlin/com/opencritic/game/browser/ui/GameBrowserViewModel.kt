package com.opencritic.game.browser.ui

import com.opencritic.game.browser.domain.BrowseGame
import com.opencritic.game.browser.domain.GameSorting
import com.opencritic.game.browser.domain.GameTimeframe
import com.opencritic.game.browser.domain.GetBrowseGamesInteractor
import com.opencritic.game.browser.domain.GetPlatformsInteractor
import com.opencritic.game.browser.domain.asTextSource
import com.opencritic.games.Platform
import com.opencritic.games.details.ui.LoadingItem
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseViewModel
import com.opencritic.navigation.GameDetailsRoute
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.asTextSource
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameBrowserViewModel(
    private val getPlatformsInteractor: GetPlatformsInteractor,
    private val getBrowseGamesInteractor: GetBrowseGamesInteractor,
    private val logger: Logger,
) : BaseViewModel<GameBrowserState>() {
    override fun initialState(): GameBrowserState =
        GameBrowserState.Loading

    private var platforms: List<Platform>? = null
    private var canLoadMore: Boolean = true
    private var sorting: GameSorting = GameSorting.Score
    private var timeframe: GameTimeframe = GameTimeframe.AllTIme
    private var platform: Platform? = null

    override fun onStateInit() {
        super.onStateInit()

        scope.launch {
            getPlatformsInteractor()
                .onFailure {
                    mutableState.update {
                        GameBrowserState.Error(it.toString())
                    }
                }
                .onSuccess { platforms ->
                    mutableState.update {
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
                )
                    .onFailure {
                        logger.log(it.toString())
                    }
                    .onSuccess { games ->
                        requireNotNull(state.value as? GameBrowserState.Content)
                            .let { content ->
                                content.copy(
                                    browseGameItems = content.browseGameItems + games.map { game ->
                                        BrowseGameItem(game)
                                    },
                                    isLoadingItemVisible = games.isNotEmpty()
                                )
                            }
                            .let { content ->
                                mutableState.update { content }
                            }

                        canLoadMore = games.isNotEmpty()
                    }
            }
        }
    }

    private fun createContentState(
        platforms: List<Platform>,
    ): GameBrowserState.Content =
        GameBrowserState.Content(
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
            browseGameItems = emptyList(),
            isLoadingItemVisible = true,
            loadingItem = LoadingItem,
            onLoadMore = { loadMore() },
            onSelectedSort = { onSortSelected(it) },
            onSelectedPlatform = { onPlatformSelected(it) },
            onSelectedTimeframe = { onTimeframeSelected(it) }
        )

    private fun loadMore() {
        if (!canLoadMore)
            return

        val state = requireNotNull(state.value as? GameBrowserState.Content)

        scope.launch {
            getBrowseGamesInteractor(
                platformCode = platform?.code ?: "",
                skip = state.browseGameItems.size,
                sorting = sorting,
                time = timeframe,
            )
                .onSuccess { reviews ->
                    logger.log("Loaded reviews count: ${reviews.size} --- $platform $sorting $timeframe ${state.browseGameItems.size}")
                    state.copy(
                        browseGameItems = state.browseGameItems + reviews.map { game ->
                            BrowseGameItem(game)
                        },
                        isLoadingItemVisible = reviews.isNotEmpty()
                    ).let {
                        mutableState.tryEmit(it)
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

        val state = requireNotNull(state.value as? GameBrowserState.Content)

        mutableState.tryEmit(
            state.copy(
                sortText = item,
                browseGameItems = emptyList(),
                isLoadingItemVisible = true
            )
        )

        loadMore()
    }

    private fun onTimeframeSelected(item: TimeframeItem) {
        if (item.key == timeframe)
            return

        timeframe = item.key
        canLoadMore = true

        val state = requireNotNull(state.value as? GameBrowserState.Content)

        mutableState.tryEmit(
            state.copy(
                timeframeText = item,
                browseGameItems = emptyList(),
                isLoadingItemVisible = true
            )
        )

        loadMore()
    }

    private fun onPlatformSelected(item: PlatformItem) {
        if (item.key == platform)
            return

        platform = item.key
        canLoadMore = true

        val state = requireNotNull(state.value as? GameBrowserState.Content)

        mutableState.tryEmit(
            state.copy(
                platformText = item,
                browseGameItems = emptyList(),
                isLoadingItemVisible = true
            )
        )

        loadMore()
    }

    private fun openGame(gameId: Long, gameName: String) {
        requireRouter()
            .navigateTo(
                GameDetailsRoute(gameId, gameName)
            )
    }
}