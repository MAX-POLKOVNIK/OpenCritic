package com.opencritic.app

import org.koin.core.KoinApplication

object IosApp : BaseApp() {
    fun init() {
        onInit()
    }

    override fun onKoinInit(koinApplication: KoinApplication) {}
}
