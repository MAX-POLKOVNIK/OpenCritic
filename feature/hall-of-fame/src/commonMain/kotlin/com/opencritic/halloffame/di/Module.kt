package com.opencritic.halloffame.di

import com.opencritic.halloffame.data.HallOfFameRepositoryImpl
import com.opencritic.halloffame.domain.GetHallsOfFameInteractor
import com.opencritic.halloffame.domain.HallOfFameRepository
import com.opencritic.halloffame.ui.HallsOfFameViewModel
import com.opencritic.mvvm.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val hallOfFameModule = module {
    single<HallOfFameRepository> { HallOfFameRepositoryImpl(get()) }

    singleOf(::GetHallsOfFameInteractor)

    viewModelOf(::HallsOfFameViewModel)
}