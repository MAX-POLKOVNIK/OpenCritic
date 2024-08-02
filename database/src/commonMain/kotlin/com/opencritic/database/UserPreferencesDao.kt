package com.opencritic.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserPreferencesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: UserPreferencesEntity)

    @Query("SELECT * FROM UserPreferencesEntity LIMIT 1")
    suspend fun get(): UserPreferencesEntity?
}