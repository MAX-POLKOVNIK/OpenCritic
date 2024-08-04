package com.opencritic.games.details.domain.interactor

import com.opencritic.auth.domain.GetAuthStateInteractor
import com.opencritic.games.Game
import com.opencritic.games.details.domain.GameDetailsRepository

class GetGameInteractor(
    private val repository: GameDetailsRepository,
    private val getAuthStateInteractor: GetAuthStateInteractor,
) {
    suspend operator fun invoke(gameId: Long): Result<Game> =
        runCatching {
            val token = getAuthStateInteractor().getOrNull()?.authToken
            repository.getGame(gameId, token)
        }
}