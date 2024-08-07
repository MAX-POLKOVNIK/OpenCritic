package com.opencritic.database

import org.koin.dsl.module

val databaseModule = module {
    registerBuilder(this)

    single<AppDatabase> { AppDatabase(get()) }
    single<UserPreferencesDao> { get<AppDatabase>().userPreferencesDao() }
}