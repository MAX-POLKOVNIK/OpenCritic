package com.opencritic.database

interface YourGameDao {
    suspend fun insert(game: YourGameEntity)
    suspend fun get(id: Long): YourGameEntity?

    suspend fun removeNotInterested()

    suspend fun getAll(): List<YourGameEntity>
    suspend fun getWanted(): List<YourGameEntity>
    suspend fun getPlayed(): List<YourGameEntity>
    suspend fun getFavorites(): List<YourGameEntity>
}