package com.opencritic.game.your.domain

class SaveYourGameInteractor(
    private val repository: YourGameRepository,
) {
    suspend operator fun invoke(game: YourGame): Result<Unit> =
        runCatching { repository.update(game) }
}