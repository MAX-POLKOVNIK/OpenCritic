package com.opencritic.dashboard.domain

interface DashboardRepository {
    suspend fun getPopularGames(): List<PosterGame>
    suspend fun getDeals(): List<GameDeal>
    suspend fun getRecentlyReleased(): List<GameItem>
    suspend fun getUpcoming(): List<GameItem>
    suspend fun getReviewedToday(): List<GameItem>
    suspend fun getHallOfFame(year: Int): List<PosterGame>
    suspend fun getSwitchFeatured(): FeaturedGameList
    suspend fun getXboxFeatured(): FeaturedGameList
    suspend fun getPlaystationFeatured(): FeaturedGameList
}