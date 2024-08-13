package com.opencritic.game.your.domain

interface GameListRepository {
    suspend fun getLists(): List<GameList>
    suspend fun getList(listId: String): GameList
    suspend fun updateGameList(
        gameListId: GameListId,
        action: GameListAction,
        game: GameInList,
    )

    suspend fun getVitalLists(): Map<GameListId, List<Long>>
}