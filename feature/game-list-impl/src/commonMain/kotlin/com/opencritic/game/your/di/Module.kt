package com.opencritic.game.your.di

import com.opencritic.game.your.data.GameListLocalRepositoryImpl
import com.opencritic.game.your.data.GameListRemoteRepositoryImpl
import com.opencritic.game.your.domain.GameListLocalRepository
import com.opencritic.game.your.domain.GetGameListInteractor
import com.opencritic.game.your.domain.GetListsInteractor
import com.opencritic.game.your.domain.UpdateGameListInteractor
import com.opencritic.game.your.domain.GameListRemoteRepository
import com.opencritic.game.your.domain.GetVitalListsInteractor
import com.opencritic.game.your.ui.list.GameListViewModel
import com.opencritic.game.your.ui.lists.YourGameListViewModel
import com.opencritic.mvvm.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val yourGamesModule = module {
    single<GameListRemoteRepository> { GameListRemoteRepositoryImpl(get(), get()) }
    single<GameListLocalRepository> { GameListLocalRepositoryImpl(get(), get(), get()) }

    singleOf(::UpdateGameListInteractor)
    singleOf(::GetListsInteractor)
    singleOf(::GetGameListInteractor)
    singleOf(::GetVitalListsInteractor)

    viewModelOf(::YourGameListViewModel)
    viewModelOf(::GameListViewModel)
}