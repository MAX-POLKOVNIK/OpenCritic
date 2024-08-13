package com.opencritic.game.your.domain

import com.opencritic.auth.domain.GetAuthStateInteractor

class GetGameListInteractor(
    private val getAuthStateInteractor: GetAuthStateInteractor,
    private val remoteRepository: GameListRemoteRepository,
    private val localRepository: GameListLocalRepository,
) {
    suspend operator fun invoke(listId: String): Result<GameList> =
        runCatching {
            if (getAuthStateInteractor().getOrThrow().isOfflineMode) {
                localRepository.getList(listId)
            } else {
                remoteRepository.getList(listId)
            }
        }
}