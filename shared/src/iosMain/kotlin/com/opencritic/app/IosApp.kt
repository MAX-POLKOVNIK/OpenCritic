package com.opencritic.app

import com.opencritic.navigation.ScreenCreator
import com.opencritic.remote.images.ImagePreloader
import org.koin.core.KoinApplication

@Suppress("unused")
object IosApp : BaseApp() {
    fun init(
        creators: List<ScreenCreator<*>>,
        imagePreloader: () -> ImagePreloader,
    ) {
        onInit(
            screenCreatorsModule(creators),
            imagePreloaderModule(imagePreloader)
        )
    }

    override fun onKoinInit(koinApplication: KoinApplication) {}
}
