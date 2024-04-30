package com.opencritic.api

import com.opencritic.api.dto.popular.PopularItemDto

interface OpenCriticsApi {
    suspend fun getGamePopular(): List<PopularItemDto>
}