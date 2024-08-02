package com.opencritic.auth.di

import com.opencritic.auth.data.AuthStateRepositoryImpl
import com.opencritic.auth.domain.AuthByCallbackInteractor
import com.opencritic.auth.domain.AuthByTokenInteractor
import com.opencritic.auth.domain.AuthRedirectInteractor
import com.opencritic.auth.domain.AuthStateRepository
import com.opencritic.auth.domain.GetAuthStateInteractor
import com.opencritic.auth.domain.SetOfflineModeInteractor
import com.opencritic.auth.ui.AuthViewModel
import com.opencritic.mvvm.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.binds
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.binds
import org.koin.dsl.module

val authModule = module {
    registerRepository()

    single<AuthStateRepository> { AuthStateRepositoryImpl(get(), get()) }

    singleOf(::GetAuthStateInteractor)
    singleOf(::SetOfflineModeInteractor)

    singleOf(::AuthByTokenInteractor)

    singleOf(::AuthByCallbackInteractor)
    singleOf(::AuthRedirectInteractor)

    viewModelOf(::AuthViewModel)
}