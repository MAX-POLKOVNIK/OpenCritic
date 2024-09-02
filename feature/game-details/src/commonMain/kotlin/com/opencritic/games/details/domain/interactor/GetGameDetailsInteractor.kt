package com.opencritic.games.details.domain.interactor

import com.opencritic.game.your.domain.GameListId
import com.opencritic.game.your.domain.YourGame
import com.opencritic.game.your.domain.GetVitalListsInteractor
import com.opencritic.games.details.domain.GameDetails
import com.opencritic.games.details.domain.GameDetailsRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class GetGameDetailsInteractor(
    private val repository: GameDetailsRepository,
    private val getVitalListsInteractor: GetVitalListsInteractor,
) {
    suspend operator fun invoke(gameId: Long): Result<GameDetails> =
        runCatching {
            coroutineScope {
                val gameDeferred = async { repository.getGame(gameId) }
                val reviewsDeferred = async { repository.getGameReviewsLanding(gameId) }
                val listsDeferred = async { getVitalListsInteractor().getOrThrow() }

                val game = gameDeferred.await()
                val reviews = reviewsDeferred.await()
                val lists = listsDeferred.await()

                val yourGame = YourGame(
                    id = gameId,
                    isWanted = lists[GameListId.Want]?.contains(gameId) ?: false,
                    isFavorite = lists[GameListId.Favorite]?.contains(gameId) ?: false,
                    isPlayed = lists[GameListId.Played]?.contains(gameId) ?: false
                )

                GameDetails(
                    id = game.id,
                    squareUrl = game.squareImageUrl,
                    bannerUrl = game.bannerImageUrl,
                    posterUrl = game.posterImageUrl,
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
}