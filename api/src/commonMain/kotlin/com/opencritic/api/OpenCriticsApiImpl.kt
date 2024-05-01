package com.opencritic.api

import com.opencritic.api.dto.deal.DealItemDto
import com.opencritic.api.dto.popular.PopularItemDto
import com.opencritic.api.dto.review.ReviewedTodayGameDto
import com.opencritic.api.dto.released.ReleasedGameDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get


private const val baseUrl = "https://api.opencritic.com/api/"

internal class OpenCriticsApiImpl(
    private val client: HttpClient,
) : OpenCriticsApi {
    override suspend fun getGamePopular(): List<PopularItemDto> =
        client.get(baseUrl + "game/popular").body()

    override suspend fun getDeals(): List<DealItemDto> =
        client.get(baseUrl + "game/deals").body()

    override suspend fun getTodayReviewed(): List<ReviewedTodayGameDto> =
        client.get(baseUrl + "game/reviewed-today").body()

    override suspend fun getUpcoming(): List<ReleasedGameDto> =
        client.get(baseUrl + "game/upcoming").body()

    override suspend fun getRecentlyReleased(): List<ReleasedGameDto> =
        client.get(baseUrl + "game/recently-released").body()
}