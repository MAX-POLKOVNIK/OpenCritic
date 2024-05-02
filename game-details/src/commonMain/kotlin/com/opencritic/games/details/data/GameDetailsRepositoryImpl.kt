package com.opencritic.games.details.data

import com.opencritic.api.OpenCriticsApi
import com.opencritic.api.dto.image.prefixedImageUrl
import com.opencritic.games.Author
import com.opencritic.games.Company
import com.opencritic.games.Game
import com.opencritic.games.GameRank
import com.opencritic.games.Outlet
import com.opencritic.games.Platform
import com.opencritic.games.Review
import com.opencritic.games.ReviewScoreFormat
import com.opencritic.games.ReviewScoreFormatOption
import com.opencritic.games.Trailer
import com.opencritic.games.details.domain.GameDetailsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

internal class GameDetailsRepositoryImpl(
    private val openCriticsApi: OpenCriticsApi,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : GameDetailsRepository {
    override suspend fun getGame(gameId: Long): Game =
        withContext(defaultDispatcher) {
            val dto = openCriticsApi.getGame(gameId)

            Game(
                id = dto.id,
                name = dto.name,
                releaseDate = dto.firstReleaseDate,
                rank = GameRank(dto.tier, dto.topCriticScore),
                recommendPercent = dto.percentRecommended.toInt().takeUnless { it < 0 },
                imageUrl = dto.images.square?.sm?.prefixedImageUrl() ?: "",
                companies = dto.companies.map { Company(it.name) },
                platforms = dto.platforms.map { Platform(it.name) },
                reviewsCount = dto.numReviews,
                trailers = dto.trailers
                    .map {
                    Trailer(
                        title = it.title,
                        thumbnailUrl = "https://img.youtube.com/vi/${it.videoId}/maxresdefault.jpg",
                        externalUrl = it.externalUrl,
                    )
                },
                screenshotUrls = dto.images.screenshots?.mapNotNull { it.sm?.prefixedImageUrl() } ?: emptyList(),
            )
        }

    override suspend fun getReviews(gameId: Long): List<Review> =
        withContext(defaultDispatcher) {
            openCriticsApi.getGameReviews(gameId)
                .map { dto ->
                    Review(
                        id = dto.id,
                        outlet = Outlet(
                            id = dto.outlet.id,
                            name = dto.outlet.name,
                            isContributor = dto.outlet.isContributor,
                            imageUrl = dto.outlet.imageSrc.sm?.prefixedImageUrl() ?: ""
                        ),
                        scoreFormat = ReviewScoreFormat(
                            id = dto.scoreFormat.id,
                            name = dto.scoreFormat.name,
                            shortName = dto.scoreFormat.name,
                            scoreDisplay = dto.scoreFormat.scoreDisplay,
                            isNumeric = dto.scoreFormat.isNumeric,
                            isSelect = dto.scoreFormat.isSelect,
                            isStars = dto.scoreFormat.isStars ?: false,
                            isPercent = dto.scoreFormat.scoreDisplay
                                ?.contains("%")
                                ?: false,
                            numDecimals = dto.scoreFormat.numDecimals,
                            base = dto.scoreFormat.base,
                            options = dto.scoreFormat.options?.map { optionDto ->
                                ReviewScoreFormatOption(
                                    value = optionDto.value,
                                    label = optionDto.label,
                                )
                            },
                        ),
                        externalUrl = dto.externalUrl,
                        platforms = dto.platforms.map { Platform(it.name) },
                        authors = dto.authors.map {
                            Author(
                                id = it.id,
                                name = it.name,
                            )
                        },
                        publishedDate = dto.publishedDate,
                        title = dto.title,
                        score = dto.score?.toInt(),
                        snippet = dto.snippet,
                    )
                }
        }

}