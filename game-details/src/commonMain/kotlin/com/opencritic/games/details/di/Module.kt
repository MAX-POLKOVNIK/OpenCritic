package com.opencritic.games.details.di

import com.opencritic.games.details.data.GameDetailsRepositoryImpl
import com.opencritic.games.details.domain.GameDetailsRepository
import com.opencritic.games.details.domain.interactor.GetAuthorInteractor
import com.opencritic.games.details.domain.interactor.GetAuthorReviewsInteractor
import com.opencritic.games.details.domain.interactor.GetGameDetailsInteractor
import com.opencritic.games.details.domain.interactor.GetGameInteractor
import com.opencritic.games.details.domain.interactor.GetGameMediaInteractor
import com.opencritic.games.details.domain.interactor.GetGameReviewsInteractor
import com.opencritic.games.details.domain.interactor.GetOutletInteractor
import com.opencritic.games.details.domain.interactor.GetOutletReviewsInteractor
import com.opencritic.games.details.ui.AuthorReviewsViewModel
import com.opencritic.games.details.ui.GameDetailsViewModel
import com.opencritic.games.details.ui.GameMediaViewModel
import com.opencritic.games.details.ui.GameReviewsViewModel
import com.opencritic.games.details.ui.OutletReviewsViewModel
import com.opencritic.mvvm.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val gameDetailsModule = module {
    singleOf(::GetGameDetailsInteractor)
    singleOf(::GetGameMediaInteractor)
    singleOf(::GetGameReviewsInteractor)
    singleOf(::GetGameInteractor)
    singleOf(::GetOutletInteractor)
    singleOf(::GetOutletReviewsInteractor)
    singleOf(::GetAuthorInteractor)
    singleOf(::GetAuthorReviewsInteractor)

    single<GameDetailsRepository> { GameDetailsRepositoryImpl(get()) }

    viewModelOf(::GameDetailsViewModel)
    viewModelOf(::GameMediaViewModel)
    viewModelOf(::GameReviewsViewModel)
    viewModelOf(::OutletReviewsViewModel)
    viewModelOf(::AuthorReviewsViewModel)
}