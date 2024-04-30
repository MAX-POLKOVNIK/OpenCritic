package com.opencritic.dashboard.ui

import com.opencritic.mvvm.ViewModelState

sealed interface DashboardState : ViewModelState {
    data object Loading : DashboardState
    data class Error(val error: String) : DashboardState
    data class Content(
        val popularGamesTitle: DashboardTitleListItem,
        val popularGames: DashboardPopularGamesHorizontalListItem,
    ) : DashboardState
}