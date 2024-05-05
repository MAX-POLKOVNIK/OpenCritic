package com.opencritic.game.your.domain

interface YourGameRepository {
    suspend fun get(gameId: Long, gameName: String): YourGame

    suspend fun update(game: YourGame)
}