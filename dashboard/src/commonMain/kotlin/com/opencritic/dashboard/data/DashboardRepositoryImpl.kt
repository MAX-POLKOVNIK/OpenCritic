package com.opencritic.dashboard.data

import com.opencritic.api.OpenCriticsApi
import com.opencritic.api.dto.image.prefixedImageUrl
import com.opencritic.dashboard.domain.DashboardRepository
import com.opencritic.dashboard.domain.GameDeal
import com.opencritic.dashboard.domain.GameItem
import com.opencritic.dashboard.domain.PopularGame
import com.opencritic.games.GameRank
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

    override suspend fun getDeals(): List<GameDeal> =
        withContext(defaultDispatcher) {
            openCriticsApi.getDeals()
                .map {
                    GameDeal(
                        game = PopularGame(
                            id = it.id,
                            name = it.name,
                            posterUrl = it.images.box.sm.prefixedImageUrl(),
                            score = it.topCriticScore.toInt(),
                            tier = Tier(it.tier)
                        ),
                        name = it.featuredDeal.name,
                        price = it.featuredDeal.price,
                    )
                }
        }

    override suspend fun getRecentlyReleased(): List<GameItem> =
        withContext(defaultDispatcher) {
            openCriticsApi.getRecentlyReleased()
                .map {
                    GameItem(
                        id = it.id,
                        name = it.name,
                        releaseDate = it.firstReleaseDate,
                        rank =
                            if (it.tier.isBlank() || it.topCriticScore < 0) null
                            else GameRank(
                                tier = Tier(it.tier),
                                score = it.topCriticScore.toInt(),
                            )
                    )
                }
        }

    override suspend fun getUpcoming(): List<GameItem> =
        withContext(defaultDispatcher) {
            openCriticsApi.getUpcoming()
                .map {
                    GameItem(
                        id = it.id,
                        name = it.name,
                        releaseDate = it.firstReleaseDate,
                        rank =
                            if (it.tier.isBlank() || it.topCriticScore < 0) null
                            else GameRank(
                                tier = Tier(it.tier),
                                score = it.topCriticScore.toInt(),
                            )
                    )
                }
        }

    override suspend fun getReviewedToday(): List<GameItem> =
        withContext(defaultDispatcher) {
            openCriticsApi.getTodayReviewed()
                .map {
                    GameItem(
                        id = it.id,
                        name = it.name,
                        releaseDate = it.firstReleaseDate,
                        rank =
                            if (it.tier.isBlank() || it.topCriticScore < 0) null
                            else GameRank(
                                tier = Tier(it.tier),
                                score = it.topCriticScore.toInt(),
                            )
                    )
                }
        }
}