package com.opencritic.dashboard.ui

import com.opencritic.dashboard.domain.GetDashboardInteractor
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseViewModel
import com.opencritic.resources.ImageResourceProvider
import com.opencritic.resources.StringResourceProvider
import com.opencritic.resources.get
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val getDashboardInteractor: GetDashboardInteractor,
    private val stringResourceProvider: StringResourceProvider,
    private val imageResourceProvider: ImageResourceProvider,
    private val logger: Logger,
) : BaseViewModel<DashboardState>() {

    override val initialState: DashboardState =
        DashboardState.Loading

    init {
        scope.launch {
            getDashboardInteractor()
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
                            popularGames = DashboardPopularGamesHorizontalListItem(
                                dashboard.popularGames,
                                imageResourceProvider,
                                {}
                            ),
                            dealsTitle = DashboardTitleListItem(
                                stringResourceProvider.featuredDeals.get(stringResourceProvider),
                                stringResourceProvider.featuredDealsDescription.get(stringResourceProvider),
                            ),
                            deals = DashboardDealsHorizontalListItem(
                                deals = dashboard.deals,
                                stringResourceProvider = stringResourceProvider,
                                imageResourceProvider = imageResourceProvider,
                                onClick = {},
                                onBuyNowClick = {},
                            ),
                            reviewedToday = DashboardSublistListItem.reviewedToday(
                                gameItems = dashboard.reviewedToday,
                                stringResourceProvider = stringResourceProvider,
                                imageResourceProvider = imageResourceProvider,
                                onItemClick = {},
                                onMoreClick = {},
                            ),
                            upcomingReleases = DashboardSublistListItem.upcomingReleases(
                                gameItems = dashboard.upcoming,
                                stringResourceProvider = stringResourceProvider,
                                imageResourceProvider = imageResourceProvider,
                                onItemClick = {},
                                onMoreClick = {},
                            ),
                            recentlyReleased = DashboardSublistListItem.recentlyReleased(
                                gameItems = dashboard.recentlyReleased,
                                stringResourceProvider = stringResourceProvider,
                                imageResourceProvider = imageResourceProvider,
                                onItemClick = {},
                                onMoreClick = {},
                            )
                        )
                    )
                }
        }
    }
}