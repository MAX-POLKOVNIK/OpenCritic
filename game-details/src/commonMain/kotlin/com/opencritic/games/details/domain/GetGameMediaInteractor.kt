package com.opencritic.games.details.domain

class GetGameMediaInteractor(
    private val repository: GameDetailsRepository,
) {
    suspend operator fun invoke(gameId: Long): Result<GameMedia> =
        runCatching {
            val game = repository.getGame(gameId)

            GameMedia(
                gameName = game.name,
                trailers = game.trailers,
                screenshotUrls = game.screenshotUrls,
            )
        }
}