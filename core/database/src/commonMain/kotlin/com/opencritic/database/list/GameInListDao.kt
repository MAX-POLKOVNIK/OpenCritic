package com.opencritic.database.list

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GameInListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(game: GameInListEntity)

    @Query("SELECT * FROM GameInListEntity WHERE id = :id")
    suspend fun get(id: Long): GameInListEntity?

    @Query("SELECT * FROM GameInListEntity")
    suspend fun getAll(): List<GameInListEntity>

    @Query("DELETE FROM GameInListEntity WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("""
        SELECT A.*
        FROM GameInListEntity A
        INNER JOIN ListGameRelationEntity B
        ON A.id = B.gameId
        WHERE B.listId = :listId
    """)
    suspend fun getAllInList(listId: String): List<GameInListEntity>
}