package com.opencritic.app

import com.opencritic.resources.ImageResourceProvider
import com.opencritic.resources.StringResourceProvider
import org.koin.core.KoinApplication

object IosApp : BaseApp() {
    fun init(
        stringResourceProvider: StringResourceProvider,
        imageResourceProvider: ImageResourceProvider,
    ) {
        onInit(stringResourceProvider, imageResourceProvider)
    }

    override fun onKoinInit(koinApplication: KoinApplication) {}
}