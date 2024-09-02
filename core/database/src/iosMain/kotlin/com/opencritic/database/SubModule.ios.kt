package com.opencritic.database

import org.koin.core.module.Module

actual fun registerBuilder(module: Module) {
    module.single { getDatabaseBuilder() }
}