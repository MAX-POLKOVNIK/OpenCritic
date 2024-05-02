package com.opencritic.games.details.domain

class GetGameDetailsInteractor(
    private val repository: GameDetailsRepository
) {
    suspend operator fun invoke(gameId: Long): Result<GameDetails> =
        runCatching {
            val game = repository.getGame(gameId)
            val reviews = repository.getReviews(gameId)

            GameDetails(
                posterUrl = game.imageUrl,
                isWanted = false,
                isPlayed = false,
                isFavorite = false,
                name = game.name,
                companies = game.companies,
                platforms = game.platforms,
                rank = game.rank,
                releaseDate = game.releaseDate,
                recommendPercent = game.recommendPercent,
                reviews = reviews
                    .filter { it.score != null }
                    .filterNot { it.scoreFormat.isSelect }
                    .take(8),
                reviewCount = game.reviewsCount,
                trailers = game.trailers,
                screenshotUrls = game.screenshotUrls,
            )
        }
}