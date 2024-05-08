package com.opencritic.games.details.domain.interactor

import com.opencritic.games.Game
import com.opencritic.games.details.domain.GameDetailsRepository

class GetGameInteractor(
    private val repository: GameDetailsRepository,
) {
    suspend operator fun invoke(gameId: Long): Result<Game> =
        runCatching {
            repository.getGame(gameId)
        }
}