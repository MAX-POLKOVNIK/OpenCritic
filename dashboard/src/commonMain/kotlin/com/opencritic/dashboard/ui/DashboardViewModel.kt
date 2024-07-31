package com.opencritic.dashboard.ui

import com.opencritic.dashboard.domain.GetDashboardInteractor
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseContentViewModel
import com.opencritic.mvvm.BaseViewModel
import com.opencritic.mvvm.CommonViewModelState
import com.opencritic.mvvm.LoadingState
import com.opencritic.navigation.GameDetailsRoute
import com.opencritic.navigation.PeriodGameBrowserDestination
import com.opencritic.navigation.PeriodGameBrowserRoute
import com.opencritic.navigation.UrlRoute
import com.opencritic.resources.DateFormatter
import com.opencritic.resources.StringProvider
import com.opencritic.resources.StringRes
import com.opencritic.resources.getFormattedString
import com.opencritic.resources.getString
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class DashboardViewModel(
    private val getDashboardInteractor: GetDashboardInteractor,
    private val stringProvider: StringProvider,
    private val dateFormatter: DateFormatter,
    private val logger: Logger,
) : BaseContentViewModel<DashboardContent>() {

    override fun initialState(): CommonViewModelState<DashboardContent> =
        CommonViewModelState.loading()

    override fun onStateInit() {
        super.onStateInit()

        scope.launch {
            val currentYear = Clock.System.now()
                .toLocalDateTime(timeZone = TimeZone.UTC)
                .year

            getDashboardInteractor(currentYear)
                .onFailure { error ->
                    mutableState.update {
                        it.error(error.toString())
                    }

                    logger.log("Get dashboard error: $error")
                }
                .onSuccess { dashboard ->
                    mutableState.update {
                        it.content(
                            DashboardContent(
                                popularGamesTitle = DashboardTitleListItem(
                                    stringProvider.getString(StringRes.str_popular_games),
                                    stringProvider.getString(StringRes.str_popular_games_description),
                                ),
                                popularGames = DashboardPosterGamesHorizontalListItem(
                                    dashboard.popularGames,
                                ) {
                                    navigateToGame(it.id, it.nameText)
                                },
                                dealsTitle = DashboardTitleListItem(
                                    stringProvider.getString(StringRes.str_featured_deals),
                                    stringProvider.getString(StringRes.str_featured_deals_description),
                                ),
                                deals = DashboardDealsHorizontalListItem(
                                    deals = dashboard.deals,
                                    stringProvider = stringProvider,
                                    onClick = { navigateToGame(it.id, it.gameDeal.game.name) },
                                    onBuyNowClick = {
                                        requireRouter()
                                            .navigateTo(
                                                UrlRoute(it.gameDeal.externalUrl)
                                            )
                                    },
                                ),
                                reviewedToday = DashboardSublistListItem.reviewedToday(
                                    gameItems = dashboard.reviewedToday,
                                    stringProvider = stringProvider,
                                    dateFormatter = dateFormatter,
                                    onItemClick = { navigateToGame(it.id, it.nameText) },
                                    onMoreClick = {
                                        requireRouter().navigateTo(
                                            PeriodGameBrowserRoute(PeriodGameBrowserDestination.Period.ReviewedThisWeek)
                                        )
                                    },
                                ),
                                upcomingReleases = DashboardSublistListItem.upcomingReleases(
                                    gameItems = dashboard.upcoming,
                                    stringProvider = stringProvider,
                                    dateFormatter = dateFormatter,
                                    onItemClick = { navigateToGame(it.id, it.nameText) },
                                    onMoreClick = {
                                        requireRouter().navigateTo(
                                            PeriodGameBrowserRoute(PeriodGameBrowserDestination.Period.UpcomingReleases)
                                        )
                                    },
                                ),
                                recentlyReleased = DashboardSublistListItem.recentlyReleased(
                                    gameItems = dashboard.recentlyReleased,
                                    stringProvider = stringProvider,
                                    dateFormatter = dateFormatter,
                                    onItemClick = { navigateToGame(it.id, it.nameText) },
                                    onMoreClick = {
                                        requireRouter().navigateTo(
                                            PeriodGameBrowserRoute(PeriodGameBrowserDestination.Period.RecentlyReleased)
                                        )
                                    },
                                ),
                                hallOfFameTitle = DashboardTitleListItem(
                                    title = stringProvider.getFormattedString(StringRes.str_hall_of_fame, currentYear.toString()),
                                    subtitle = stringProvider.getFormattedString(StringRes.str_hall_of_fame_description, currentYear.toString())
                                ),
                                hallOfFame = DashboardPosterGamesHorizontalListItem(
                                    popularGames = dashboard.hallOfFame,
                                    onClick = { navigateToGame(it.id, it.nameText) }
                                ),
                                whoIsMightyMan = DashboardMightyManListItem(
                                    stringProvider,
                                ),
                                switchTitle = DashboardTitleListItem(
                                    title = dashboard.switchFeatured.name,
                                    subtitle = dashboard.switchFeatured.description,
                                ),
                                switchGames = DashboardPosterGamesHorizontalListItem(
                                    popularGames = dashboard.switchFeatured.games,
                                    onClick = { navigateToGame(it.id, it.nameText) }
                                ),
                                xboxTitle = DashboardTitleListItem(
                                    title = dashboard.xboxFeatured.name,
                                    subtitle = dashboard.xboxFeatured.description,
                                ),
                                xboxGames = DashboardPosterGamesHorizontalListItem(
                                    popularGames = dashboard.xboxFeatured.games,
                                    onClick = { navigateToGame(it.id, it.nameText) }
                                ),
                                playstationTitle = DashboardTitleListItem(
                                    title = dashboard.playstationFeatured.name,
                                    subtitle = dashboard.playstationFeatured.description,
                                ),
                                playstationGames = DashboardPosterGamesHorizontalListItem(
                                    popularGames = dashboard.playstationFeatured.games,
                                    onClick = { navigateToGame(it.id, it.nameText) }
                                ),
                            )
                        )
                    }
                }
        }
    }

    private fun navigateToGame(gameId: Long, gameName: String) =
        requireRouter()
            .navigateTo(
                GameDetailsRoute(gameId, gameName)
            )
}