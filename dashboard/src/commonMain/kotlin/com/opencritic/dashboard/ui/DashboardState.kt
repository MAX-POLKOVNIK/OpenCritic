package com.opencritic.dashboard.ui

import com.opencritic.mvvm.ViewModelState

sealed interface DashboardState : ViewModelState {
    data object Loading : DashboardState
    data class Error(val error: String) : DashboardState
    data class Content(
        val popularGamesTitle: DashboardTitleListItem,
        val popularGames: DashboardPosterGamesHorizontalListItem,
        val dealsTitle: DashboardTitleListItem,
        val deals: DashboardDealsHorizontalListItem,
        val recentlyReleased: DashboardSublistListItem,
        val upcomingReleases: DashboardSublistListItem,
        val reviewedToday: DashboardSublistListItem,
        val hallOfFameTitle: DashboardTitleListItem,
        val hallOfFame: DashboardPosterGamesHorizontalListItem,
        val whoIsMightyMan: DashboardMightyManListItem,
    ) : DashboardState
}