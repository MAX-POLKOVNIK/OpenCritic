package com.opencritic.app

import com.opencritic.resources.StringProvider
import org.koin.core.KoinApplication

object IosApp : BaseApp() {
    fun init(
        stringProvider: StringProvider,
    ) {
        onInit(stringProvider)
    }

    override fun onKoinInit(koinApplication: KoinApplication) {}
}
