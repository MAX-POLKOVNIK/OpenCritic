package com.opencritic.about.di

import com.opencritic.about.domain.GetAndroidAppVersionInteractor
import com.opencritic.about.domain.GetAppVersionInteractor
import org.koin.core.module.Module

actual fun Module.registerInteractor() {
    single<GetAppVersionInteractor> { GetAndroidAppVersionInteractor(get()) }
}