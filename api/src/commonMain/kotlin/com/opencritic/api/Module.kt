package com.opencritic.api

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val apiModule = module {
    singleOf(::HttpClient)
    singleOf(::OpenCriticsApiImpl) { bind<OpenCriticsApi>() }
}