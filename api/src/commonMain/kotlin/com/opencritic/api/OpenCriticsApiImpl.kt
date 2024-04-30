package com.opencritic.api

import com.opencritic.api.dto.popular.PopularItemDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get


private const val baseUrl = "https://api.opencritic.com/api/"

internal class OpenCriticsApiImpl(
    private val client: HttpClient,
) : OpenCriticsApi {
    override suspend fun getGamePopular(): List<PopularItemDto> =
        client.get(baseUrl + "game/popular").body()
}