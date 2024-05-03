package com.opencritic.api

import com.opencritic.api.dto.deal.DealItemDto
import com.opencritic.api.dto.details.GameDetailsDto
import com.opencritic.api.dto.featured.FeaturedGameListDto
import com.opencritic.api.dto.popular.PopularItemDto
import com.opencritic.api.dto.review.ReviewedTodayGameDto
import com.opencritic.api.dto.released.ReleasedGameDto
import com.opencritic.api.dto.review.ReviewDto
import com.opencritic.api.dto.search.SearchItemDto

interface OpenCriticsApi {
    suspend fun getGamePopular(): List<PopularItemDto>

    suspend fun getDeals(): List<DealItemDto>

    suspend fun getTodayReviewed(): List<ReviewedTodayGameDto>

    suspend fun getUpcoming(): List<ReleasedGameDto>

    suspend fun getRecentlyReleased(): List<ReleasedGameDto>

    suspend fun getHallOfFame(year: Int): List<ReleasedGameDto>

    suspend fun getSwitchFeatured(): FeaturedGameListDto

    suspend fun getXboxFeatured(): FeaturedGameListDto

    suspend fun getPlaystationFeatured(): FeaturedGameListDto

    suspend fun getGame(gameId: Long): GameDetailsDto

    suspend fun getGameReviews(gameId: Long, skip: Int = 0): List<ReviewDto>

    suspend fun search(criteria: String): List<SearchItemDto>
}