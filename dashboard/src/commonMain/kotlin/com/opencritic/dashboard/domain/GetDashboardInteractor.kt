package com.opencritic.dashboard.domain

class GetDashboardInteractor(
    private val dashboardRepository: DashboardRepository,
) {
    suspend operator fun invoke(year: Int): Result<Dashboard> =
        runCatching {
            Dashboard(
                popularGames = dashboardRepository.getPopularGames(),
                deals = dashboardRepository.getDeals(),
                recentlyReleased = dashboardRepository.getRecentlyReleased(),
                upcoming = dashboardRepository.getUpcoming(),
                reviewedToday = dashboardRepository.getReviewedToday(),
                hallOfFame = dashboardRepository.getHallOfFame(year)
            )
        }
}