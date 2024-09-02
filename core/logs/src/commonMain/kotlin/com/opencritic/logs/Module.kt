package com.opencritic.logs

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val logsModule = module {
    singleOf(::Logger)
}