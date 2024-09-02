package com.opencritic.auth.di

import com.opencritic.auth.api.domain.GetAuthStateInteractor
import com.opencritic.auth.api.domain.SetOfflineModeInteractor
import com.opencritic.auth.data.AuthStateRepositoryImpl
import com.opencritic.auth.domain.AuthByCallbackInteractor
import com.opencritic.auth.domain.AuthByTokenInteractor
import com.opencritic.auth.domain.AuthRedirectInteractor
import com.opencritic.auth.domain.AuthStateRepository
import com.opencritic.auth.domain.GetAuthStateInteractorImpl
import com.opencritic.auth.domain.SetOfflineModeInteractorImpl
import com.opencritic.auth.ui.AuthViewModel
import com.opencritic.mvvm.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authModule = module {
    registerRepository()

    single<AuthStateRepository> { AuthStateRepositoryImpl(get(), get()) }

    singleOf(::GetAuthStateInteractorImpl) { bind<GetAuthStateInteractor>() }
    singleOf(::SetOfflineModeInteractorImpl) { bind<SetOfflineModeInteractor>() }

    singleOf(::AuthByTokenInteractor)

    singleOf(::AuthByCallbackInteractor)
    singleOf(::AuthRedirectInteractor)

    viewModelOf(::AuthViewModel)
}