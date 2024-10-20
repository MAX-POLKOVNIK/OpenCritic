package com.opencritic.about.di

import com.opencritic.about.domain.GetAppVersionInteractor
import com.opencritic.about.domain.GetIosAppVersionInteractor
import org.koin.core.module.Module

actual fun Module.registerInteractor() {
    single<GetAppVersionInteractor> { GetIosAppVersionInteractor() }
}