package com.opencritic.app

import org.koin.core.KoinApplication
import platform.Foundation.NSLog

object IosApp : BaseApp() {
    fun init() {
        onInit()
        NSLog("Hello from KMP")
    }

    override fun onKoinInit(koinApplication: KoinApplication) {

    }
}