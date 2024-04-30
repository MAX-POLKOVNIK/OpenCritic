package com.opencritic.app

import com.opencritic.api.apiModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

abstract class BaseApp {
    internal fun onInit() {
        startKoin {
            onKoinInit(this)

            modules(
                apiModule
            )
        }
    }

    protected abstract fun onKoinInit(koinApplication: KoinApplication)
}