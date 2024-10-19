package com.opencritic.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.opencritic.database.list.GameInListDao
import com.opencritic.database.list.GameInListEntity
import com.opencritic.database.list.GameListDao
import com.opencritic.database.list.GameListEntity
import com.opencritic.database.list.GameTier
import com.opencritic.database.list.ListGameRelationDao
import com.opencritic.database.list.ListGameRelationEntity
import com.opencritic.database.preferences.UserPreferencesDao
import com.opencritic.database.preferences.UserPreferencesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(
    entities = [
        UserPreferencesEntity::class,
        GameInListEntity::class,
        GameListEntity::class,
        ListGameRelationEntity::class,
    ],
    version = 4,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userPreferencesDao(): UserPreferencesDao
    abstract fun gameInListDao(): GameInListDao
    abstract fun gameListDao(): GameListDao
    abstract fun listGameRelationDao(): ListGameRelationDao

    companion object {
        operator fun invoke(builder: Builder<AppDatabase>): AppDatabase =
            builder
                .fallbackToDestructiveMigration(true)
                .setDriver(BundledSQLiteDriver())
                .setQueryCoroutineContext(Dispatchers.IO)
                .build()
    }
}