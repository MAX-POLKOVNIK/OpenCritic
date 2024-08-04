package com.opencritic.game.your.domain

class UpdateGameListInteractor(
    private val yourGameRepository: YourGameRepository,
) {
    suspend operator fun invoke(
        gameListId: GameListId,
        action: GameListAction,
        gameId: Long,
    ): Result<Unit> =
        runCatching { yourGameRepository.updateGameList(gameListId, action, gameId) }
}