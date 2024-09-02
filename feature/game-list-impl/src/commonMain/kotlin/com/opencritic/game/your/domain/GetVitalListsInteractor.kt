package com.opencritic.game.your.domain

import com.opencritic.auth.api.domain.GetAuthStateInteractor

class GetVitalListsInteractor(
    private val getAuthStateInteractor: GetAuthStateInteractor,
    private val gameListRemoteRepository: GameListRemoteRepository,
    private val gameListLocalRepository: GameListLocalRepository,
) {
    suspend operator fun invoke(): Result<Map<GameListId, List<Long>>> =
        runCatching {
            if (getAuthStateInteractor().getOrThrow().isOfflineMode) {
                gameListLocalRepository.getVitalLists()
            } else {
                gameListRemoteRepository.getVitalLists()
            }
        }
}