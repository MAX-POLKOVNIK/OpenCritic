package com.opencritic.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface YourGameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(game: YourGameEntity)

    @Query("SELECT * FROM YourGameEntity WHERE id = :id")
    suspend fun get(id: Long): YourGameEntity?

    @Query("DELETE FROM YourGameEntity WHERE isInterested = false")
    suspend fun removeNotInterested()

    @Query("SELECT * FROM YourGameEntity")
    suspend fun getAll(): List<YourGameEntity>

    @Query("SELECT * FROM YourGameEntity WHERE isWanted = true")
    suspend fun getWanted(): List<YourGameEntity>

    @Query("SELECT * FROM YourGameEntity WHERE isPlayed = true")
    suspend fun getPlayed(): List<YourGameEntity>

    @Query("SELECT * FROM YourGameEntity WHERE isFavorite = true")
    suspend fun getFavorites(): List<YourGameEntity>
}