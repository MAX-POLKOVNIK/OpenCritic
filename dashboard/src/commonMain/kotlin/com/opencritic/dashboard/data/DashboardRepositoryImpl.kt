package com.opencritic.dashboard.data

import com.opencritic.api.OpenCriticsApi
import com.opencritic.api.dto.image.prefixedImageUrl
import com.opencritic.dashboard.domain.DashboardRepository
import com.opencritic.dashboard.domain.PopularGame
import com.opencritic.games.Tier
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class DashboardRepositoryImpl(
    private val openCriticsApi: OpenCriticsApi,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : DashboardRepository {
    override suspend fun getPopularGames(): List<PopularGame> =
        withContext(defaultDispatcher) {
            openCriticsApi.getGamePopular()
                .map {
                    PopularGame(
                        id = it.id,
                        name = it.name,
                        posterUrl = it.images.box.sm.prefixedImageUrl(),
                        score = it.topCriticScore.toInt(),
                        tier = Tier(it.tier)
                    )
                }
        }
}