package com.opencritic.main.di

import com.opencritic.main.ui.MainViewModel
import com.opencritic.mvvm.viewModelOf
import org.koin.dsl.module

val mainModule = module {
    viewModelOf(::MainViewModel)
}