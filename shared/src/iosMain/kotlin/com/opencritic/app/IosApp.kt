package com.opencritic.app

import com.opencritic.resources.DateFormatter
import com.opencritic.resources.ImageResourceProvider
import com.opencritic.resources.StringResourceProvider
import org.koin.core.KoinApplication

object IosApp : BaseApp() {
    fun init(
        stringResourceProvider: StringResourceProvider,
        imageResourceProvider: ImageResourceProvider,
        dateFormatter: DateFormatter,
    ) {
        onInit(stringResourceProvider, imageResourceProvider, dateFormatter)
    }

    override fun onKoinInit(koinApplication: KoinApplication) {}
}