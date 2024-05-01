package com.opencritic.dashboard.ui

import com.opencritic.dashboard.domain.GetDashboardInteractor
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseViewModel
import com.opencritic.navigation.GameDetailsRoute
import com.opencritic.resources.DateFormatter
import com.opencritic.resources.ImageResourceProvider
import com.opencritic.resources.StringResourceProvider
import com.opencritic.resources.get
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class DashboardViewModel(
    private val getDashboardInteractor: GetDashboardInteractor,
    private val stringResourceProvider: StringResourceProvider,
    private val imageResourceProvider: ImageResourceProvider,
    private val dateFormatter: DateFormatter,
    private val logger: Logger,
) : BaseViewModel<DashboardState>() {

    override val initialState: DashboardState =
        DashboardState.Loading

    init {
        scope.launch {
            val currentYear = Clock.System.now()
                .toLocalDateTime(timeZone = TimeZone.UTC)
                .year

            getDashboardInteractor(currentYear)
                .onFailure {
                    mutableState.tryEmit(
                        DashboardState.Error(it.toString())
                    )
                    logger.log("Get dashboard error: $it")
                }
                .onSuccess { dashboard ->
                    mutableState.tryEmit(
                        DashboardState.Content(
                            popularGamesTitle = DashboardTitleListItem(
                                stringResourceProvider.popularGames.get(stringResourceProvider),
                                stringResourceProvider.popularGamesDescription.get(stringResourceProvider),
                            ),
                            popularGames = DashboardPosterGamesHorizontalListItem(
                                dashboard.popularGames,
                                imageResourceProvider,
                            ) {
                                navigateToGame(it.id)
                            },
                            dealsTitle = DashboardTitleListItem(
                                stringResourceProvider.featuredDeals.get(stringResourceProvider),
                                stringResourceProvider.featuredDealsDescription.get(stringResourceProvider),
                            ),
                            deals = DashboardDealsHorizontalListItem(
                                deals = dashboard.deals,
                                stringResourceProvider = stringResourceProvider,
                                imageResourceProvider = imageResourceProvider,
                                onClick = { navigateToGame(it.id) },
                                onBuyNowClick = {},
                            ),
                            reviewedToday = DashboardSublistListItem.reviewedToday(
                                gameItems = dashboard.reviewedToday,
                                stringResourceProvider = stringResourceProvider,
                                imageResourceProvider = imageResourceProvider,
                                dateFormatter = dateFormatter,
                                onItemClick = { navigateToGame(it.id) },
                                onMoreClick = {},
                            ),
                            upcomingReleases = DashboardSublistListItem.upcomingReleases(
                                gameItems = dashboard.upcoming,
                                stringResourceProvider = stringResourceProvider,
                                imageResourceProvider = imageResourceProvider,
                                dateFormatter = dateFormatter,
                                onItemClick = { navigateToGame(it.id) },
                                onMoreClick = {},
                            ),
                            recentlyReleased = DashboardSublistListItem.recentlyReleased(
                                gameItems = dashboard.recentlyReleased,
                                stringResourceProvider = stringResourceProvider,
                                imageResourceProvider = imageResourceProvider,
                                dateFormatter = dateFormatter,
                                onItemClick = { navigateToGame(it.id) },
                                onMoreClick = {},
                            ),
                            hallOfFameTitle = DashboardTitleListItem(
                                title = stringResourceProvider.hallOfFameFormatted.get(stringResourceProvider, currentYear.toString()),
                                subtitle = stringResourceProvider.hallOfFameDescriptionFormatted.get(stringResourceProvider, currentYear.toString())
                            ),
                            hallOfFame = DashboardPosterGamesHorizontalListItem(
                                popularGames = dashboard.hallOfFame,
                                imageResourceProvider = imageResourceProvider,
                                onClick = { navigateToGame(it.id) }
                            ),
                            whoIsMightyMan = DashboardMightyManListItem(
                                stringResourceProvider,
                                imageResourceProvider
                            ),
                            switchTitle = DashboardTitleListItem(
                                title = dashboard.switchFeatured.name,
                                subtitle = dashboard.switchFeatured.description,
                            ),
                            switchGames = DashboardPosterGamesHorizontalListItem(
                                popularGames = dashboard.switchFeatured.games,
                                imageResourceProvider = imageResourceProvider,
                                onClick = { navigateToGame(it.id) }
                            ),
                            xboxTitle = DashboardTitleListItem(
                                title = dashboard.xboxFeatured.name,
                                subtitle = dashboard.xboxFeatured.description,
                            ),
                            xboxGames = DashboardPosterGamesHorizontalListItem(
                                popularGames = dashboard.xboxFeatured.games,
                                imageResourceProvider = imageResourceProvider,
                                onClick = { navigateToGame(it.id) }
                            ),
                            playstationTitle = DashboardTitleListItem(
                                title = dashboard.playstationFeatured.name,
                                subtitle = dashboard.playstationFeatured.description,
                            ),
                            playstationGames = DashboardPosterGamesHorizontalListItem(
                                popularGames = dashboard.playstationFeatured.games,
                                imageResourceProvider = imageResourceProvider,
                                onClick = { navigateToGame(it.id) }
                            ),
                        )
                    )
                }
        }
    }

    private fun navigateToGame(gameId: Long) =
        requireRouter()
            .navigateTo(
                GameDetailsRoute(gameId)
            )
}