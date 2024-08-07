package com.opencritic.dashboard.domain

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class GetDashboardInteractor(
    private val dashboardRepository: DashboardRepository,
) {
    suspend operator fun invoke(year: Int): Result<Dashboard> =
        runCatching {
            coroutineScope {
                val popularGames = async { dashboardRepository.getPopularGames() }
                val deals = async { dashboardRepository.getDeals() }
                val recentlyReleased = async { dashboardRepository.getRecentlyReleased() }
                val upcoming = async { dashboardRepository.getUpcoming() }
                val reviewedToday = async { dashboardRepository.getReviewedToday() }
                val hallOfFame = async { dashboardRepository.getHallOfFame(year) }
                val switchFeatured = async { dashboardRepository.getSwitchFeatured() }
                val xboxFeatured = async { dashboardRepository.getXboxFeatured() }
                val playstationFeatured = async { dashboardRepository.getPlaystationFeatured() }

                Dashboard(
                    popularGames = popularGames.await(),
                    deals = deals.await(),
                    recentlyReleased = recentlyReleased.await(),
                    upcoming = upcoming.await(),
                    reviewedToday = reviewedToday.await(),
                    hallOfFame = hallOfFame.await(),
                    switchFeatured = switchFeatured.await(),
                    xboxFeatured = xboxFeatured.await(),
                    playstationFeatured = playstationFeatured.await(),
                )
            }
        }
}