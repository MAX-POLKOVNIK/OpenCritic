package com.opencritic.auth.di

import com.opencritic.auth.domain.AuthByCallbackInteractor
import com.opencritic.auth.domain.AuthRedirectInteractor
import com.opencritic.auth.ui.AuthViewModel
import com.opencritic.mvvm.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authModule = module {
    registerRepository()

    singleOf(::AuthByCallbackInteractor)
    singleOf(::AuthRedirectInteractor)

    viewModelOf(::AuthViewModel)
}