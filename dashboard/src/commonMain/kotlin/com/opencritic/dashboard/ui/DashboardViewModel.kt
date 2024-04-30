package com.opencritic.dashboard.ui

import com.opencritic.dashboard.domain.GetDashboardInteractor
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseViewModel
import com.opencritic.resources.ImageResourceProvider
import com.opencritic.resources.StringResourceProvider
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
                                stringResourceProvider.get(
                                    stringResourceProvider.popularGames,
                                ),
                                stringResourceProvider.get(
                                    stringResourceProvider.popularGames,
                                )
                            ),
                            popularGames = DashboardPopularGamesHorizontalListItem(
                                dashboard.popularGames,
                                imageResourceProvider,
                                {}
                            )
                        )
                    )
                }
        }
    }
}