package com.opencritic.game.browser.di

import com.opencritic.game.browser.data.GameBrowserRepositoryImpl
import com.opencritic.game.browser.domain.GameBrowserRepository
import com.opencritic.game.browser.domain.GetBrowseGamesInteractor
import com.opencritic.game.browser.domain.GetPlatformsInteractor
import com.opencritic.game.browser.ui.GameBrowserViewModel
import com.opencritic.mvvm.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val gameBrowserModule = module {
    single<GameBrowserRepository> { GameBrowserRepositoryImpl(get()) }

    singleOf(::GetPlatformsInteractor)
    singleOf(::GetBrowseGamesInteractor)

    viewModelOf(::GameBrowserViewModel)
}