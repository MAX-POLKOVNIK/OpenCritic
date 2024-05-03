package com.opencritic.games.details.domain.interactor

import com.opencritic.games.details.domain.GameDetails
import com.opencritic.games.details.domain.GameDetailsRepository

class GetGameDetailsInteractor(
    private val repository: GameDetailsRepository
) {
    suspend operator fun invoke(gameId: Long): Result<GameDetails> =
        runCatching {
            val game = repository.getGame(gameId)
            val reviews = repository.getReviews(gameId)

            GameDetails(
                posterUrl = game.squareImageUrl,
                isWanted = false,
                isPlayed = false,
                isFavorite = false,
                name = game.name,
                companies = game.companies,
                platforms = game.platforms,
                rank = game.rank,
                releaseDate = game.releaseDate,
                recommendPercent = game.recommendPercent,
                reviews = reviews,
                reviewCount = game.reviewsCount,
                trailers = game.trailers,
                screenshotUrls = game.screenshotUrls,
            )
        }
}