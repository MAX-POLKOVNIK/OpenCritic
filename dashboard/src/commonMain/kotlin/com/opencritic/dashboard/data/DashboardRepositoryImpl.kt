package com.opencritic.dashboard.data

import com.opencritic.api.OpenCriticsApi
import com.opencritic.api.dto.image.prefixedImageUrl
import com.opencritic.dashboard.domain.DashboardRepository
import com.opencritic.dashboard.domain.FeaturedGameList
import com.opencritic.dashboard.domain.GameDeal
import com.opencritic.dashboard.domain.GameItem
import com.opencritic.dashboard.domain.PosterGame
import com.opencritic.games.GameRank
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class DashboardRepositoryImpl(
    private val openCriticsApi: OpenCriticsApi,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : DashboardRepository {
    override suspend fun getPopularGames(): List<PosterGame> =
        withContext(defaultDispatcher) {
            openCriticsApi.getGamePopular()
                .map {
                    PosterGame(
                        id = it.id,
                        name = it.name,
                        posterUrl = it.images.box?.sm?.prefixedImageUrl() ?: it.images.banner?.sm?.prefixedImageUrl() ?: "",
                        rank = GameRank(it.tier, it.topCriticScore),
                    )
                }
        }

    override suspend fun getDeals(): List<GameDeal> =
        withContext(defaultDispatcher) {
            openCriticsApi.getDeals()
                .map {
                    GameDeal(
                        game = PosterGame(
                            id = it.id,
                            name = it.name,
                            posterUrl = it.images.box?.sm?.prefixedImageUrl() ?: it.images.banner?.sm?.prefixedImageUrl() ?: "",
                            rank = GameRank(it.tier, it.topCriticScore),
                        ),
                        name = it.featuredDeal.name,
                        price = it.featuredDeal.price,
                        externalUrl = it.featuredDeal.externalUrl
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
                        rank = GameRank(it.tier, it.topCriticScore),
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
                        rank = GameRank(it.tier, it.topCriticScore),
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
                        rank = GameRank(it.tier, it.topCriticScore),
                    )
                }
        }

    override suspend fun getHallOfFame(year: Int): List<PosterGame> =
        withContext(defaultDispatcher) {
            openCriticsApi.getHallOfFame(year)
                .map {
                    PosterGame(
                        id = it.id,
                        name = it.name,
                        posterUrl = it.images.box?.sm?.prefixedImageUrl() ?: it.images.banner?.sm?.prefixedImageUrl() ?: "",
                        rank = GameRank(it.tier, it.topCriticScore),
                    )
                }
        }

    override suspend fun getSwitchFeatured(): FeaturedGameList =
        withContext(defaultDispatcher) {
            val dto = openCriticsApi.getSwitchFeatured()

            FeaturedGameList(
                name = dto.label,
                description = dto.description,
                games = dto.games.map {
                    PosterGame(
                        id = it.id,
                        name = it.name,
                        posterUrl = it.images.box?.sm?.prefixedImageUrl() ?: it.images.banner?.sm?.prefixedImageUrl() ?: "",
                        rank = GameRank(it.tier, it.topCriticScore),
                    )
                }
            )
        }

    override suspend fun getXboxFeatured(): FeaturedGameList =
        withContext(defaultDispatcher) {
            val dto = openCriticsApi.getXboxFeatured()

            FeaturedGameList(
                name = dto.label,
                description = dto.description,
                games = dto.games.map {
                    PosterGame(
                        id = it.id,
                        name = it.name,
                        posterUrl = it.images.box?.sm?.prefixedImageUrl() ?: it.images.banner?.sm?.prefixedImageUrl() ?: "",
                        rank = GameRank(it.tier, it.topCriticScore),
                    )
                }
            )
        }

    override suspend fun getPlaystationFeatured(): FeaturedGameList =
        withContext(defaultDispatcher) {
            val dto = openCriticsApi.getPlaystationFeatured()

            FeaturedGameList(
                name = dto.label,
                description = dto.description,
                games = dto.games.map {
                    PosterGame(
                        id = it.id,
                        name = it.name,
                        posterUrl = it.images.box?.sm?.prefixedImageUrl() ?: it.images.banner?.sm?.prefixedImageUrl() ?: "",
                        rank = GameRank(it.tier, it.topCriticScore),
                    )
                }
            )
        }
}