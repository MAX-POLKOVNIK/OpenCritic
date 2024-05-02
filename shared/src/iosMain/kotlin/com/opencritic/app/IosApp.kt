package com.opencritic.app

import com.opencritic.resources.DateFormatter
import com.opencritic.resources.ImageResourceProvider
import com.opencritic.resources.StringProvider
import org.koin.core.KoinApplication

object IosApp : BaseApp() {
    fun init(
        stringProvider: StringProvider,
        imageResourceProvider: ImageResourceProvider,
        dateFormatter: DateFormatter,
    ) {
        onInit(stringProvider, imageResourceProvider, dateFormatter)
    }

    override fun onKoinInit(koinApplication: KoinApplication) {}
}