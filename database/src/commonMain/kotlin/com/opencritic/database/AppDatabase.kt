package com.opencritic.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(entities = [YourGameEntity::class, UserPreferencesEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun yourGameDao(): YourGameDao
    abstract fun userPreferencesDao(): UserPreferencesDao

    companion object {
        operator fun invoke(builder: Builder<AppDatabase>): AppDatabase =
            builder
                .fallbackToDestructiveMigration(true)
                .setDriver(BundledSQLiteDriver())
                .setQueryCoroutineContext(Dispatchers.IO)
                .build()
    }
}