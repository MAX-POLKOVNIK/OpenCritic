package com.opencritic.dashboard.domain

interface DashboardRepository {
    suspend fun getPopularGames(): List<PopularGame>
    suspend fun getDeals(): List<GameDeal>
}