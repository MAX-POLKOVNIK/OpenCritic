package com.opencritic.games.details.domain.interactor

import com.opencritic.games.details.domain.GameDetailsRepository
import com.opencritic.games.details.domain.GameMedia

class GetGameMediaInteractor(
    private val repository: GameDetailsRepository,
) {
    suspend operator fun invoke(gameId: Long): Result<GameMedia> =
        runCatching {
            val game = repository.getGameMedia(gameId)

            GameMedia(
                gameName = game.name,
                trailers = game.trailers,
                screenshotUrls = game.screenshotUrls,
            )
        }
}