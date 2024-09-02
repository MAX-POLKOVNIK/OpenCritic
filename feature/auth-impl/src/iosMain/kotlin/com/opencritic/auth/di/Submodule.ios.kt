package com.opencritic.auth.di

import com.opencritic.auth.data.AuthRepositoryImpl
import com.opencritic.auth.domain.AuthRepository
import org.koin.core.module.Module

actual fun Module.registerRepository() {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
}
