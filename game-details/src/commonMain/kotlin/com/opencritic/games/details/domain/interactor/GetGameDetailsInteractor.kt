package com.opencritic.games.details.domain.interactor

import com.opencritic.game.your.domain.GameListId
import com.opencritic.game.your.domain.YourGame
import com.opencritic.game.your.domain.YourGameRepository
import com.opencritic.games.details.domain.GameDetails
import com.opencritic.games.details.domain.GameDetailsRepository

class GetGameDetailsInteractor(
    private val repository: GameDetailsRepository,
    private val yourGameRepository: YourGameRepository,
) {
    suspend operator fun invoke(gameId: Long): Result<GameDetails> =
        runCatching {
            val game = repository.getGame(gameId)
            val reviews = repository.getGameReviewsLanding(gameId)
            val lists = yourGameRepository.getVitalLists()

            val yourGame = YourGame(
                id = gameId,
                isWanted = lists[GameListId.Want]?.contains(gameId) ?: false,
                isFavorite = lists[GameListId.Favorite]?.contains(gameId) ?: false,
                isPlayed = lists[GameListId.Played]?.contains(gameId) ?: false
            )

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
                url = game.url,
            )
        }
}