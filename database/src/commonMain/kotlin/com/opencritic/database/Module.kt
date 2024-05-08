package com.opencritic.database

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val databaseModule = module {
    single { createRealm() }

    singleOf(::YourGameDaoImpl) { bind<YourGameDao>() }
}