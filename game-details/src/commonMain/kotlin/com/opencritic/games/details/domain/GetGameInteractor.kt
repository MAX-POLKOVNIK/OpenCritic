package com.opencritic.games.details.domain

import com.opencritic.games.Game

class GetGameInteractor(
    private val repository: GameDetailsRepository,
) {
    suspend operator fun invoke(gameId: Long): Result<Game> =
        runCatching {
            repository.getGame(gameId)
        }
}