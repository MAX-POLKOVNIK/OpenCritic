package com.opencritic.dashboard.domain

interface DashboardRepository {
    suspend fun getPopularGames(): List<PopularGame>
    suspend fun getDeals(): List<GameDeal>
    suspend fun getRecentlyReleased(): List<GameItem>
    suspend fun getUpcoming(): List<GameItem>
    suspend fun getReviewedToday(): List<GameItem>
}