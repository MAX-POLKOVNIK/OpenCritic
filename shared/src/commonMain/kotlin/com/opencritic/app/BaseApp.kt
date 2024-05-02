package com.opencritic.app

import com.opencritic.api.apiModule
import com.opencritic.dashboard.di.dashboardModule
import com.opencritic.games.details.di.gameDetailsModule
import com.opencritic.logs.Logger
import com.opencritic.logs.logsModule
import com.opencritic.main.di.mainModule
import com.opencritic.resources.DateFormatter
import com.opencritic.resources.ImageResourceProvider
import com.opencritic.resources.StringResourceProvider
import com.opencritic.resources.resourcesModule
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

abstract class BaseApp : KoinComponent {
    private val logger: Logger by inject()

    internal fun onInit(
        stringResourceProvider: StringResourceProvider,
        imageResourceProvider: ImageResourceProvider,
        dateFormatter: DateFormatter,
    ) {
        startKoin {
            onKoinInit(this)

            modules(
                apiModule,
                logsModule,
                resourcesModule(stringResourceProvider, imageResourceProvider, dateFormatter),
                dashboardModule,
                mainModule,
                gameDetailsModule,
            )
        }

        logger.log("Hello from KMP")
    }

    protected abstract fun onKoinInit(koinApplication: KoinApplication)
}