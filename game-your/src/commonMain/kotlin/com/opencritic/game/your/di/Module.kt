package com.opencritic.game.your.di

import com.opencritic.game.your.data.YourGameRepositoryImpl
import com.opencritic.game.your.domain.GetListsInteractor
import com.opencritic.game.your.domain.UpdateGameListInteractor
import com.opencritic.game.your.domain.YourGameRepository
import com.opencritic.game.your.ui.YourGameListViewModel
import com.opencritic.mvvm.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val yourGamesModule = module {
    single<YourGameRepository> { YourGameRepositoryImpl(get(), get()) }

    singleOf(::UpdateGameListInteractor)
    singleOf(::GetListsInteractor)

    viewModelOf(::YourGameListViewModel)
}