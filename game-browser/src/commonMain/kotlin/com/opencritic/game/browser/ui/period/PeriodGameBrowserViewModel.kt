package com.opencritic.game.browser.ui.period

import com.opencritic.game.browser.domain.BrowseGame
import com.opencritic.game.browser.domain.GameSorting
import com.opencritic.game.browser.domain.GameTimeframe
import com.opencritic.game.browser.domain.GetBrowseGamesInteractor
import com.opencritic.game.browser.domain.GetReviewedThisWeekInteractor
import com.opencritic.game.browser.ui.BrowseGameItem
import com.opencritic.games.details.ui.LoadingItem
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseViewModel
import com.opencritic.navigation.GameDetailsRoute
import com.opencritic.navigation.PeriodGameBrowserDestination
import com.opencritic.resources.DateFormatter
import com.opencritic.resources.ImageResourceProvider
import com.opencritic.resources.StringProvider
import kotlinx.coroutines.launch

class PeriodGameBrowserViewModel(
    private val period: PeriodGameBrowserDestination.Period,
    private val getBrowseGamesInteractor: GetBrowseGamesInteractor,
    private val getReviewedThisWeekInteractor: GetReviewedThisWeekInteractor,
    private val stringProvider: StringProvider,
    private val imageResourceProvider: ImageResourceProvider,
    private val dateFormatter: DateFormatter,
    private val logger: Logger,
) : BaseViewModel<PeriodGameBrowserState>() {
    override val initialState: PeriodGameBrowserState =
        PeriodGameBrowserState.Loading(titleFor(period))

    private var canLoadMore: Boolean = period != PeriodGameBrowserDestination.Period.ReviewedThisWeek

    init {
        scope.launch {
            when (period) {
                PeriodGameBrowserDestination.Period.ReviewedThisWeek ->
                    getReviewedThisWeekInteractor()
                PeriodGameBrowserDestination.Period.RecentlyReleased ->
                    getBrowseGamesInteractor(
                        platformCode = "",
                        skip = 0,
                        sorting = GameSorting.ReleaseDate,
                        time = GameTimeframe.Last90Days,
                    )
                PeriodGameBrowserDestination.Period.UpcomingReleases ->
                    getBrowseGamesInteractor(
                        platformCode = "",
                        skip = 0,
                        sorting = GameSorting.ReleaseDate,
                        time = GameTimeframe.Upcoming,
                    )
            }
                .onFailure {
                    logger.log(it.toString())
                }
                .onSuccess { games ->
                    createContentState()
                        .let { content ->
                            content.copy(
                                browseGameItems = content.browseGameItems + games.map { game ->
                                    BrowseGameItem(game)
                                },
                                isLoadingItemVisible = games.isNotEmpty() && period != PeriodGameBrowserDestination.Period.ReviewedThisWeek
                            )
                        }
                        .let {
                            mutableState.tryEmit(it)
                        }

                    canLoadMore = games.isNotEmpty()
                }
        }
    }

    private fun titleFor(period: PeriodGameBrowserDestination.Period): String =
        when (period){
            PeriodGameBrowserDestination.Period.ReviewedThisWeek -> stringProvider.reviewedThisWeek
            PeriodGameBrowserDestination.Period.RecentlyReleased -> stringProvider.recentlyReleased
            PeriodGameBrowserDestination.Period.UpcomingReleases -> stringProvider.upcomingReleases
        }

    private fun createContentState(): PeriodGameBrowserState.Content =
        PeriodGameBrowserState.Content(
            titleText = titleFor(period),
            browseGameItems = emptyList(),
            isLoadingItemVisible = true,
            loadingItem = LoadingItem,
            onLoadMore = { loadMore() },
        )

    private fun loadMore() {
        if (!canLoadMore || period == PeriodGameBrowserDestination.Period.ReviewedThisWeek)
            return

        val state = requireNotNull(state.value as? PeriodGameBrowserState.Content)

        scope.launch {
            when (period) {
                PeriodGameBrowserDestination.Period.ReviewedThisWeek ->
                    getReviewedThisWeekInteractor()
                PeriodGameBrowserDestination.Period.RecentlyReleased ->
                    getBrowseGamesInteractor(
                        platformCode = "",
                        skip = state.browseGameItems.size,
                        sorting = GameSorting.ReleaseDate,
                        time = GameTimeframe.Last90Days,
                    )
                PeriodGameBrowserDestination.Period.UpcomingReleases ->
                    getBrowseGamesInteractor(
                        platformCode = "",
                        skip = state.browseGameItems.size,
                        sorting = GameSorting.ReleaseDate,
                        time = GameTimeframe.Upcoming,
                    )
            }
                .onSuccess { reviews ->
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
            isPercentRecommendedVisible = false,
            imageResourceProvider = imageResourceProvider,
            dateFormatter = dateFormatter,
            onClick = { openGame(game.id, game.name) },
        )

    private fun openGame(gameId: Long, gameName: String) {
        requireRouter()
            .navigateTo(
                GameDetailsRoute(gameId, gameName)
            )
    }
}