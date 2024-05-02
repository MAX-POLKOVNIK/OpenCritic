package com.opencritic.games.details.di

import com.opencritic.games.details.data.GameDetailsRepositoryImpl
import com.opencritic.games.details.domain.GameDetailsRepository
import com.opencritic.games.details.domain.GetGameDetailsInteractor
import com.opencritic.games.details.ui.GameDetailsViewModel
import com.opencritic.mvvm.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val gameDetailsModule = module {
    singleOf(::GetGameDetailsInteractor)

    single<GameDetailsRepository> { GameDetailsRepositoryImpl(get()) }

    viewModelOf(::GameDetailsViewModel)
}