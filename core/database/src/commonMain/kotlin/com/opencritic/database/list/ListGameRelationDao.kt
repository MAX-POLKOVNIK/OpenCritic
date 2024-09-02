package com.opencritic.database.list

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ListGameRelationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: ListGameRelationEntity)

    @Query("SELECT * FROM ListGameRelationEntity WHERE listId = :listId")
    suspend fun getByListId(listId: String): List<ListGameRelationEntity>

    @Query("SELECT * FROM ListGameRelationEntity WHERE gameId = :gameId")
    suspend fun getByGameId(gameId: Long): List<ListGameRelationEntity>

    @Query("SELECT * FROM ListGameRelationEntity")
    suspend fun getAll(): List<ListGameRelationEntity>

    @Delete
    suspend fun delete(item: ListGameRelationEntity)
}