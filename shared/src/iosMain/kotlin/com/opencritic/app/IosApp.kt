package com.opencritic.app

import com.opencritic.resources.DateFormatter
import com.opencritic.resources.StringProvider
import org.koin.core.KoinApplication

object IosApp : BaseApp() {
    fun init(
        stringProvider: StringProvider,
        dateFormatter: DateFormatter,
    ) {
        onInit(stringProvider, dateFormatter)
    }

    override fun onKoinInit(koinApplication: KoinApplication) {}
}
