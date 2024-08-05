package com.opencritic.dashboard.ui

import com.opencritic.mvvm.ScreenContent

data class DashboardContent(
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
) : ScreenContent