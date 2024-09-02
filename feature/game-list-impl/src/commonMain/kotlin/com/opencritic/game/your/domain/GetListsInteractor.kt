package com.opencritic.game.your.domain

import com.opencritic.auth.api.domain.GetAuthStateInteractor

class GetListsInteractor(
    private val getAuthStateInteractor: GetAuthStateInteractor,
    private val gameListRemoteRepository: GameListRemoteRepository,
    private val gameListLocalRepository: GameListLocalRepository,
) {
    suspend operator fun invoke(): Result<List<GameList>> =
        runCatching {
            if (getAuthStateInteractor().getOrThrow().isOfflineMode) {
                gameListLocalRepository.getLists()
            } else {
                gameListRemoteRepository.getLists()
            }
        }
}