package com.opencritic.app

import com.opencritic.navigation.ScreenCreator
import org.koin.core.KoinApplication

@Suppress("unused")
object IosApp : BaseApp() {
    fun init(creators: List<ScreenCreator<*>>) {
        onInit(
            screenCreatorsModule(creators)
        )
    }

    override fun onKoinInit(koinApplication: KoinApplication) {}
}
