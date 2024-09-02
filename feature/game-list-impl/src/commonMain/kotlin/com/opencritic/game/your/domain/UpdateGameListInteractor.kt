package com.opencritic.game.your.domain

import com.opencritic.auth.api.domain.GetAuthStateInteractor

class UpdateGameListInteractor(
    private val getAuthStateInteractor: GetAuthStateInteractor,
    private val gameListRemoteRepository: GameListRemoteRepository,
    private val gameListLocalRepository: GameListLocalRepository,
) {
    suspend operator fun invoke(
        gameListId: GameListId,
        action: GameListAction,
        game: GameInList,
    ): Result<Unit> =
        runCatching {
            if (getAuthStateInteractor().getOrThrow().isOfflineMode) {
                gameListLocalRepository.updateGameList(gameListId, action, game)
            } else {
                gameListRemoteRepository.updateGameList(gameListId, action, game)
            }
        }
}