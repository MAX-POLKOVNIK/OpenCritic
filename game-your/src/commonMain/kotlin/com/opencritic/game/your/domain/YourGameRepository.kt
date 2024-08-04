package com.opencritic.game.your.domain

interface YourGameRepository {
    suspend fun getLists(): List<GameList>
    suspend fun updateGameList(
        gameListId: GameListId,
        action: GameListAction,
        gameId: Long,
    )

    suspend fun getVitalLists(): Map<GameListId, List<Long>>
}