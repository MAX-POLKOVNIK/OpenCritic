package com.opencritic.game.your.domain

class GetYourGameInteractor(
    private val repository: YourGameRepository,
) {
    suspend operator fun invoke(gameId: Long, gameName: String): Result<YourGame> =
        runCatching { repository.get(gameId, gameName) }
}