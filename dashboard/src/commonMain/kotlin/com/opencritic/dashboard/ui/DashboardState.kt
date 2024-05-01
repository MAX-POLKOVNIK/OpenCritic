package com.opencritic.dashboard.ui

import com.opencritic.mvvm.ViewModelState

sealed interface DashboardState : ViewModelState {
    data object Loading : DashboardState
    data class Error(val error: String) : DashboardState
    data class Content(
        val popularGamesTitle: DashboardTitleListItem,
        val popularGames: DashboardPopularGamesHorizontalListItem,
        val dealsTitle: DashboardTitleListItem,
        val deals: DashboardDealsHorizontalListItem,
        val recentlyReleased: DashboardSublistListItem,
        val upcomingReleases: DashboardSublistListItem,
        val reviewedToday: DashboardSublistListItem,
    ) : DashboardState
}