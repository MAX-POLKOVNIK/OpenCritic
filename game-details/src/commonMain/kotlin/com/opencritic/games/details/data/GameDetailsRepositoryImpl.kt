package com.opencritic.games.details.data

import com.opencritic.api.OpenCriticsApi
import com.opencritic.api.dto.details.GameDetailsDto
import com.opencritic.api.dto.image.prefixedImageUrl
import com.opencritic.api.dto.review.ReviewDto
import com.opencritic.api.dto.review.ReviewSortKey
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
import com.opencritic.games.details.domain.ReviewSorting
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

internal class GameDetailsRepositoryImpl(
    private val openCriticsApi: OpenCriticsApi,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : GameDetailsRepository {
    override suspend fun getGame(gameId: Long, token: String?): Game =
        withContext(defaultDispatcher) {
            openCriticsApi.getGame(gameId, token).toGame()
        }

    override suspend fun getGameMedia(gameId: Long): Game =
        withContext(defaultDispatcher) {
            openCriticsApi.getGameMedia(gameId).toGame()
        }

    override suspend fun getGameReviewsLanding(gameId: Long): List<Review> =
        withContext(defaultDispatcher) {
            openCriticsApi.getGameReviewsLanding(gameId)
                .map { it.toModel() }
        }

    override suspend fun getGameReviews(gameId: Long, skip: Int, sort: ReviewSorting): List<Review> =
        withContext(defaultDispatcher) {
            openCriticsApi
                .getGameReviews(
                    gameId = gameId,
                    skip = skip,
                    sort = sort.toDto(),
                )
                .map { it.toModel() }
        }

    override suspend fun getOutlet(outletId: Int): com.opencritic.games.details.domain.Outlet =
        withContext(defaultDispatcher) {
            val dto = openCriticsApi.getOutlet(outletId)

            com.opencritic.games.details.domain.Outlet(
                id = dto.id,
                imageUrl = dto.imageSrc.og?.prefixedImageUrl() ?: "",
                percentRecommended = dto.percentRecommended,
                reviewsCount = dto.numReviews,
                medianScore = dto.medianScore.toInt(),
                averageScore = dto.averageScore,
                name = dto.name,
                externalUrl = dto.externalUrl,
            )
        }

    override suspend fun getOutletReviews(outletId: Int, skip: Int, sort: ReviewSorting): List<Review> =
        withContext(defaultDispatcher) {
            openCriticsApi
                .getOutletReviews(
                    outletId = outletId,
                    skip = skip,
                    sort = sort.toDto(),
                )
                .map { it.toModel() }
        }

    override suspend fun getAuthor(authorId: Int): com.opencritic.games.details.domain.Author =
        withContext(defaultDispatcher) {
            val dto = openCriticsApi.getAuthor(authorId)

            com.opencritic.games.details.domain.Author(
                id = dto.id,
                name = dto.name,
                imageUrl = dto.imageSrc?.lg?.prefixedImageUrl() ?: "",
                isClaimed = dto.claimed,
                percentRecommended = dto.percentRecommended,
                reviewCount = dto.numReviews,
                medianScore = dto.medianScore.toInt(),
                averageScore = dto.averageScore,
                favoriteGames = dto.favoriteGames ?: emptyList(),
                bio = dto.bio ?: "",
                hometown = dto.hometown ?: "",
                xboxLive = dto.xboxLive ?: "",
                psn = dto.psn ?: "",
            )
        }

    override suspend fun getAuthorReviews(
        authorId: Int,
        skip: Int,
        sort: ReviewSorting
    ): List<Review> =
        withContext(defaultDispatcher) {
            openCriticsApi
                .getAuthorReviews(
                    authorId = authorId,
                    skip = skip,
                    sort = sort.toDto(),
                )
                .map { it.toModel() }
        }

    private fun GameDetailsDto.toGame(): Game =
        Game(
            id = id,
            name = name,
            releaseDate = firstReleaseDate,
            rank = GameRank(tier, topCriticScore),
            recommendPercent = percentRecommended.takeUnless { it < 0f },
            squareImageUrl = images.square?.sm?.prefixedImageUrl() ?: "",
            bannerImageUrl = images.banner?.sm?.prefixedImageUrl() ?: "",
            companies = companies.map { Company(it.name) },
            platforms = platforms.map { Platform(it.name, it.shortName) },
            reviewsCount = numReviews,
            trailers = trailers
                .map {
                    Trailer(
                        title = it.title,
                        thumbnailUrl = "https://img.youtube.com/vi/${it.videoId}/maxresdefault.jpg",
                        externalUrl = it.externalUrl,
                    )
                },
            screenshotUrls = images.screenshots?.mapNotNull { it.sm?.prefixedImageUrl() } ?: emptyList(),
        )

    private fun ReviewSorting.toDto(): ReviewSortKey =
        when (this) {
            ReviewSorting.Default -> ReviewSortKey.Default
            ReviewSorting.MostPopular -> ReviewSortKey.Popularity
            ReviewSorting.ScoreHighestToLowest -> ReviewSortKey.ScoreHigh
            ReviewSorting.ScoreLowestToHighest -> ReviewSortKey.ScoreLow
            ReviewSorting.NewestFirst -> ReviewSortKey.Newest
            ReviewSorting.OldestFirst -> ReviewSortKey.Oldest
        }

    private fun ReviewDto.toModel(): Review =
        Review(
            id = id,
            outlet = Outlet(
                id = outlet.id,
                name = outlet.name,
                isContributor = outlet.isContributor,
                imageUrl =outlet.imageSrc.sm?.prefixedImageUrl() ?: ""
            ),
            scoreFormat = ReviewScoreFormat(
                id = scoreFormat.id,
                name = scoreFormat.name,
                shortName = scoreFormat.name,
                scoreDisplay = scoreFormat.scoreDisplay,
                isNumeric = scoreFormat.isNumeric,
                isSelect = scoreFormat.isSelect,
                isStars = scoreFormat.isStars ?: false,
                isPercent = scoreFormat.scoreDisplay
                    ?.contains("%")
                    ?: false,
                numDecimals = scoreFormat.numDecimals,
                base = scoreFormat.base,
                options = scoreFormat.options?.map { optionDto ->
                    ReviewScoreFormatOption(
                        value = optionDto.value,
                        label = optionDto.label,
                    )
                },
            ),
            externalUrl = externalUrl,
            platforms = platforms.map { Platform(it.name, it.shortName) },
            authors = authors.map {
                Author(
                    id = it.id,
                    name = it.name,
                    imageUrl = it.imageSrc?.sm?.prefixedImageUrl()
                )
            },
            alias = alias,
            publishedDate = publishedDate,
            title = title,
            score = score,
            snippet = snippet ?: "",
            gameId = game.id,
            gameName = game.name,
            youtubePlaceholderUrl = youtubeVideoId?.let { "https://img.youtube.com/vi/$it/maxresdefault.jpg" },
        )
}