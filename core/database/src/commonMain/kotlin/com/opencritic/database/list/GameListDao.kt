package com.opencritic.database.list

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GameListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: GameListEntity)

    @Query("SELECT * FROM GameListEntity WHERE id = :id")
    suspend fun get(id: String): GameListEntity?

    @Query("SELECT * FROM GameListEntity WHERE vitalType = :type")
    suspend fun getByVitalType(type: VitalType): GameListEntity?

    @Query("SELECT * FROM GameListEntity")
    suspend fun getAll(): List<GameListEntity>

    @Query("DELETE FROM GameListEntity WHERE id = :id")
    suspend fun delete(id: String)
}