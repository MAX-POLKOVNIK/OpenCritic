package com.opencritic.dashboard.di

import com.opencritic.dashboard.data.DashboardRepositoryImpl
import com.opencritic.dashboard.domain.DashboardRepository
import com.opencritic.dashboard.domain.GetDashboardInteractor
import com.opencritic.dashboard.ui.DashboardViewModel
import com.opencritic.mvvm.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dashboardModule = module {
    viewModelOf(::DashboardViewModel)

    single<DashboardRepository> { DashboardRepositoryImpl(get()) }

    singleOf(::GetDashboardInteractor)
}