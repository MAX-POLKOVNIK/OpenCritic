package com.opencritic.dashboard.ui

import com.opencritic.dashboard.domain.GetDashboardInteractor
import com.opencritic.game.browser.api.PeriodGameBrowserRoute
import com.opencritic.games.details.api.ui.GameDetailsRoute
import com.opencritic.halloffame.api.HallOfFameRoute
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseContentViewModel
import com.opencritic.mvvm.CommonViewModelState
import com.opencritic.navigation.UrlRoute
import com.opencritic.navigation.asUrlRouteArgs
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.asTextSource
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class DashboardViewModel(
    private val getDashboardInteractor: GetDashboardInteractor,
    private val logger: Logger,
) : BaseContentViewModel<DashboardContent>() {

    override fun initialState(): CommonViewModelState<DashboardContent> =
        CommonViewModelState.loading(title = StringRes.str_tab_main.asTextSource())

    override fun onStateInit() {
        super.onStateInit()

        loadDashboard()
    }

    private fun loadDashboard() {
        scope.launch {
            showLoading()

            val currentYear = Clock.System.now()
                .toLocalDateTime(timeZone = TimeZone.UTC)
                .year

            getDashboardInteractor(currentYear)
                .onFailure { error ->
                    showError(error) {
                        loadDashboard()
                    }
                    logger.log("Get dashboard error: $error")
                }
                .onSuccess { dashboard ->
                    setContent {
                        DashboardContent(
                            popularGamesTitle = DashboardTitleListItem(
                                StringRes.str_popular_games.asTextSource(),
                                StringRes.str_popular_games_description.asTextSource(),
                            ),
                            popularGames = DashboardPosterGamesHorizontalListItem(
                                dashboard.popularGames,
                            ) {
                                navigateToGame(it.id, it.nameText)
                            },
                            dealsTitle = DashboardTitleListItem(
                                StringRes.str_featured_deals.asTextSource(),
                                StringRes.str_featured_deals_description.asTextSource(),
                            ),
                            deals = DashboardDealsHorizontalListItem(
                                deals = dashboard.deals,
                                onClick = { navigateToGame(it.id, it.gameDeal.game.name) },
                                onBuyNowClick = {
                                    UrlRoute.navigate(it.gameDeal.externalUrl.asUrlRouteArgs())
                                },
                            ),
                            reviewedToday = DashboardSublistListItem.reviewedToday(
                                gameItems = dashboard.reviewedToday,
                                onItemClick = { navigateToGame(it.id, it.nameText) },
                                onMoreClick = {
                                    PeriodGameBrowserRoute.navigate(
                                        PeriodGameBrowserRoute.InitArgs(PeriodGameBrowserRoute.InitArgs.Period.ReviewedThisWeek)
                                    )
                                },
                            ),
                            upcomingReleases = DashboardSublistListItem.upcomingReleases(
                                gameItems = dashboard.upcoming,
                                onItemClick = { navigateToGame(it.id, it.nameText) },
                                onMoreClick = {
                                    PeriodGameBrowserRoute.navigate(
                                        PeriodGameBrowserRoute.InitArgs(
                                            PeriodGameBrowserRoute.InitArgs.Period.UpcomingReleases
                                        )
                                    )
                                },
                            ),
                            recentlyReleased = DashboardSublistListItem.recentlyReleased(
                                gameItems = dashboard.recentlyReleased,
                                onItemClick = { navigateToGame(it.id, it.nameText) },
                                onMoreClick = {
                                    PeriodGameBrowserRoute.navigate(
                                        PeriodGameBrowserRoute.InitArgs(PeriodGameBrowserRoute.InitArgs.Period.RecentlyReleased)
                                    )
                                },
                            ),
                            hallOfFameTitle = DashboardTitleListItem(
                                title = StringRes.str_hall_of_fame.asTextSource(currentYear.toString()),
                                subtitle = StringRes.str_hall_of_fame_description.asTextSource(currentYear.toString()),
                                buttonTitle = StringRes.str_dashboard_view_all_hall_of_fame.asTextSource(),
                                onButtonClick = { navigateToHallOfFame() }
                            ),
                            hallOfFame = DashboardPosterGamesHorizontalListItem(
                                popularGames = dashboard.hallOfFame,
                                onClick = { navigateToGame(it.id, it.nameText) }
                            ),
                            whoIsMightyMan = DashboardMightyManListItem(),
                            switchTitle = DashboardTitleListItem(
                                title = dashboard.switchFeatured.name.asTextSource(),
                                subtitle = dashboard.switchFeatured.description.asTextSource(),
                            ),
                            switchGames = DashboardPosterGamesHorizontalListItem(
                                popularGames = dashboard.switchFeatured.games,
                                onClick = { navigateToGame(it.id, it.nameText) }
                            ),
                            xboxTitle = DashboardTitleListItem(
                                title = dashboard.xboxFeatured.name.asTextSource(),
                                subtitle = dashboard.xboxFeatured.description.asTextSource(),
                            ),
                            xboxGames = DashboardPosterGamesHorizontalListItem(
                                popularGames = dashboard.xboxFeatured.games,
                                onClick = { navigateToGame(it.id, it.nameText) }
                            ),
                            playstationTitle = DashboardTitleListItem(
                                title = dashboard.playstationFeatured.name.asTextSource(),
                                subtitle = dashboard.playstationFeatured.description.asTextSource(),
                            ),
                            playstationGames = DashboardPosterGamesHorizontalListItem(
                                popularGames = dashboard.playstationFeatured.games,
                                onClick = { navigateToGame(it.id, it.nameText) }
                            ),
                        )
                    }
                }
        }
    }

    private fun navigateToHallOfFame() =
        HallOfFameRoute.navigate(HallOfFameRoute.InitArgs)

    private fun navigateToGame(gameId: Long, gameName: String) =
        GameDetailsRoute.navigate(
            GameDetailsRoute.InitArgs(gameId, gameName)
        )
}