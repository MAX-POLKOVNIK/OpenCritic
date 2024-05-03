package com.opencritic.games.details.di

import com.opencritic.games.details.data.GameDetailsRepositoryImpl
import com.opencritic.games.details.domain.GameDetailsRepository
import com.opencritic.games.details.domain.GetGameDetailsInteractor
import com.opencritic.games.details.domain.GetGameInteractor
import com.opencritic.games.details.domain.GetGameMediaInteractor
import com.opencritic.games.details.domain.GetGameReviewsInteractor
import com.opencritic.games.details.ui.GameDetailsViewModel
import com.opencritic.games.details.ui.GameMediaViewModel
import com.opencritic.games.details.ui.GameReviewsViewModel
import com.opencritic.mvvm.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val gameDetailsModule = module {
    singleOf(::GetGameDetailsInteractor)
    singleOf(::GetGameMediaInteractor)
    singleOf(::GetGameReviewsInteractor)
    singleOf(::GetGameInteractor)

    single<GameDetailsRepository> { GameDetailsRepositoryImpl(get()) }

    viewModelOf(::GameDetailsViewModel)
    viewModelOf(::GameMediaViewModel)
    viewModelOf(::GameReviewsViewModel)
}