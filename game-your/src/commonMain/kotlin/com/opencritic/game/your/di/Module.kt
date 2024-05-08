package com.opencritic.game.your.di

import com.opencritic.game.your.data.YourGameRepositoryImpl
import com.opencritic.game.your.domain.GetYourGameInteractor
import com.opencritic.game.your.domain.GetYourGameListInteractor
import com.opencritic.game.your.domain.SaveYourGameInteractor
import com.opencritic.game.your.domain.YourGameRepository
import com.opencritic.game.your.ui.YourGameListViewModel
import com.opencritic.mvvm.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val yourGamesModule = module {
    single<YourGameRepository> { YourGameRepositoryImpl(get()) }

    singleOf(::GetYourGameInteractor)
    singleOf(::SaveYourGameInteractor)
    singleOf(::GetYourGameListInteractor)

    viewModelOf(::YourGameListViewModel)
}