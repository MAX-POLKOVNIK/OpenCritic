package com.opencritic.api

import com.opencritic.api.dto.deal.DealItemDto
import com.opencritic.api.dto.popular.PopularItemDto
import com.opencritic.api.dto.review.ReviewedTodayGameDto
import com.opencritic.api.dto.released.ReleasedGameDto

interface OpenCriticsApi {
    suspend fun getGamePopular(): List<PopularItemDto>

    suspend fun getDeals(): List<DealItemDto>

    suspend fun getTodayReviewed(): List<ReviewedTodayGameDto>

    suspend fun getUpcoming(): List<ReleasedGameDto>

    suspend fun getRecentlyReleased(): List<ReleasedGameDto>

    suspend fun getHallOfFame(year: Int): List<ReleasedGameDto>
}