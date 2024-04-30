package com.opencritic.dashboard.domain

class GetDashboardInteractor(
    private val dashboardRepository: DashboardRepository,
) {
    suspend operator fun invoke(): Result<Dashboard> =
        runCatching {
            Dashboard(
               popularGames = dashboardRepository.getPopularGames(),
            )
        }
}