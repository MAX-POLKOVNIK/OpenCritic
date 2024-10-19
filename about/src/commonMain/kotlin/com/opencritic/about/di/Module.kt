package com.opencritic.about.di

import com.opencritic.about.ui.AboutViewModel
import com.opencritic.mvvm.viewModelOf
import org.koin.dsl.module

val aboutModule = module {
    registerInteractor(this)

    viewModelOf(::AboutViewModel)
}