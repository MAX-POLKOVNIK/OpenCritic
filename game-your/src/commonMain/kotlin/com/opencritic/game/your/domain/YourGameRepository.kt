package com.opencritic.game.your.domain

interface YourGameRepository {
    suspend fun get(gameId: Long, gameName: String): YourGame

    suspend fun update(game: YourGame)

    suspend fun remoteNotInterested()

    suspend fun getAll(): List<YourGame>
    suspend fun getWanted(): List<YourGame>
    suspend fun getPlayed(): List<YourGame>
    suspend fun getFavorites(): List<YourGame>

    suspend fun getLists(): List<GameList>
}