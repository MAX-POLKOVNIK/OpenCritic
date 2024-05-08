package com.opencritic.dashboard.ui

import com.opencritic.mvvm.BaseErrorState
import com.opencritic.mvvm.BaseLoadingState
import com.opencritic.mvvm.ViewModelState

sealed interface DashboardState : ViewModelState {
    data object Loading : BaseLoadingState(), DashboardState
    data class Error(val error: String) : BaseErrorState(error), DashboardState
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
        val switchTitle: DashboardTitleListItem,
        val switchGames: DashboardPosterGamesHorizontalListItem,
        val xboxTitle: DashboardTitleListItem,
        val xboxGames: DashboardPosterGamesHorizontalListItem,
        val playstationTitle: DashboardTitleListItem,
        val playstationGames: DashboardPosterGamesHorizontalListItem,
    ) : DashboardState
}