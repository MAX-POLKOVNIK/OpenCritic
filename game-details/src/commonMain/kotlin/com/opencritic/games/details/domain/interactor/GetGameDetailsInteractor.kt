package com.opencritic.games.details.domain.interactor

import com.opencritic.game.your.domain.GetYourGameInteractor
import com.opencritic.games.details.domain.GameDetails
import com.opencritic.games.details.domain.GameDetailsRepository

class GetGameDetailsInteractor(
    private val repository: GameDetailsRepository,
    private val getYourGameInteractor: GetYourGameInteractor,
) {
    suspend operator fun invoke(gameId: Long): Result<GameDetails> =
        runCatching {
            val game = repository.getGame(gameId)
            val reviews = repository.getGameReviewsLanding(gameId)

            val yourGame = getYourGameInteractor(gameId, game.name)
                .getOrThrow()

            GameDetails(
                posterUrl = game.squareImageUrl,
                yourGame = yourGame,
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