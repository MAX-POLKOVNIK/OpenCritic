package com.opencritic.app

import com.opencritic.api.apiModule
import com.opencritic.auth.di.authModule
import com.opencritic.dashboard.di.dashboardModule
import com.opencritic.database.databaseModule
import com.opencritic.game.browser.di.gameBrowserModule
import com.opencritic.game.your.di.yourGamesModule
import com.opencritic.games.details.di.gameDetailsModule
import com.opencritic.logs.Logger
import com.opencritic.logs.logsModule
import com.opencritic.main.di.mainModule
import com.opencritic.resources.StringProvider
import com.opencritic.resources.resourcesModule
import com.opencritic.search.di.searchModule
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

abstract class BaseApp : KoinComponent {
    private val logger: Logger by inject()

    internal fun onInit(
        stringProvider: StringProvider,
    ) {
        startKoin {
            onKoinInit(this)

            modules(
                apiModule,
                logsModule,
                resourcesModule(stringProvider),
                dashboardModule,
                mainModule,
                gameDetailsModule,
                searchModule,
                gameBrowserModule,
                databaseModule,
                yourGamesModule,
                authModule,
            )
        }

        logger.log("Hello from KMP")
    }

    protected abstract fun onKoinInit(koinApplication: KoinApplication)
}