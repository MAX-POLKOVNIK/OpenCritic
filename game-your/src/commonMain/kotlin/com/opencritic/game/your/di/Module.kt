package com.opencritic.game.your.di

import com.opencritic.game.your.data.YourGameRepositoryImpl
import com.opencritic.game.your.domain.GetYourGameInteractor
import com.opencritic.game.your.domain.SaveYourGameInteractor
import com.opencritic.game.your.domain.YourGameRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val yourGamesModule = module {
    single<YourGameRepository> { YourGameRepositoryImpl(get()) }

    singleOf(::GetYourGameInteractor)
    singleOf(::SaveYourGameInteractor)
}