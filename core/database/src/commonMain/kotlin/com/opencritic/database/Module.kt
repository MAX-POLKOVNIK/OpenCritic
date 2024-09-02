package com.opencritic.database

import com.opencritic.database.list.GameInListDao
import com.opencritic.database.list.GameListDao
import com.opencritic.database.list.ListGameRelationDao
import com.opencritic.database.preferences.UserPreferencesDao
import org.koin.dsl.module

val databaseModule = module {
    registerBuilder(this)

    single<AppDatabase> { AppDatabase(get()) }
    single<UserPreferencesDao> { get<AppDatabase>().userPreferencesDao() }
    single<GameInListDao> { get<AppDatabase>().gameInListDao() }
    single<GameListDao> { get<AppDatabase>().gameListDao() }
    single<ListGameRelationDao> { get<AppDatabase>().listGameRelationDao() }
}