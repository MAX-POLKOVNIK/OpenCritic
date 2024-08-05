package com.opencritic.game.browser.ui.period

import com.opencritic.game.browser.domain.BrowseGame
import com.opencritic.game.browser.domain.GameSorting
import com.opencritic.game.browser.domain.GameTimeframe
import com.opencritic.game.browser.domain.GetBrowseGamesInteractor
import com.opencritic.game.browser.domain.GetReviewedThisWeekInteractor
import com.opencritic.game.browser.ui.BrowseGameItem
import com.opencritic.games.details.ui.LoadingItem
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseContentViewModel
import com.opencritic.mvvm.BaseViewModel
import com.opencritic.mvvm.CommonViewModelState
import com.opencritic.navigation.GameDetailsRoute
import com.opencritic.navigation.PeriodGameBrowserDestination
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PeriodGameBrowserViewModel(
    private val period: PeriodGameBrowserDestination.Period,
    private val getBrowseGamesInteractor: GetBrowseGamesInteractor,
    private val getReviewedThisWeekInteractor: GetReviewedThisWeekInteractor,
    private val logger: Logger,
) : BaseContentViewModel<PeriodGameBrowserContent>() {
    override fun initialState(): CommonViewModelState<PeriodGameBrowserContent> =
        CommonViewModelState.loading(titleFor(period))

    private var canLoadMore: Boolean = period != PeriodGameBrowserDestination.Period.ReviewedThisWeek

    override fun onStateInit() {
        super.onStateInit()

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
                        .let { content ->
                            setContent { content }
                        }

                    canLoadMore = games.isNotEmpty()
                }
        }
    }

    private fun titleFor(period: PeriodGameBrowserDestination.Period): TextSource =
        when (period) {
            PeriodGameBrowserDestination.Period.ReviewedThisWeek -> StringRes.str_reviewed_this_week.asTextSource()
            PeriodGameBrowserDestination.Period.RecentlyReleased -> StringRes.str_recently_released.asTextSource()
            PeriodGameBrowserDestination.Period.UpcomingReleases -> StringRes.str_upcoming_releases.asTextSource()
        }

    private fun createContentState(): PeriodGameBrowserContent =
        PeriodGameBrowserContent(
            browseGameItems = emptyList(),
            isLoadingItemVisible = true,
            loadingItem = LoadingItem,
            onLoadMore = { loadMore() },
        )

    private fun loadMore() {
        if (!canLoadMore || period == PeriodGameBrowserDestination.Period.ReviewedThisWeek)
            return

        val content = requireContent()

        scope.launch {
            when (period) {
                PeriodGameBrowserDestination.Period.ReviewedThisWeek ->
                    getReviewedThisWeekInteractor()
                PeriodGameBrowserDestination.Period.RecentlyReleased ->
                    getBrowseGamesInteractor(
                        platformCode = "",
                        skip = content.browseGameItems.size,
                        sorting = GameSorting.ReleaseDate,
                        time = GameTimeframe.Last90Days,
                    )
                PeriodGameBrowserDestination.Period.UpcomingReleases ->
                    getBrowseGamesInteractor(
                        platformCode = "",
                        skip = content.browseGameItems.size,
                        sorting = GameSorting.ReleaseDate,
                        time = GameTimeframe.Upcoming,
                    )
            }
                .onSuccess { reviews ->
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
            isPercentRecommendedVisible = false,
            onClick = { openGame(game.id, game.name) },
        )

    private fun openGame(gameId: Long, gameName: String) {
        requireRouter()
            .navigateTo(
                GameDetailsRoute(gameId, gameName)
            )
    }
}