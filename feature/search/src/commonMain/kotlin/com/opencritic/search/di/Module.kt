package com.opencritic.search.di

import com.opencritic.mvvm.viewModelOf
import com.opencritic.search.data.SearchRepositoryImpl
import com.opencritic.search.domain.SearchInteractor
import com.opencritic.search.domain.SearchRepository
import com.opencritic.search.ui.SearchViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val searchModule = module {
    viewModelOf(::SearchViewModel)

    singleOf(::SearchInteractor)

    single<SearchRepository> { SearchRepositoryImpl(get()) }
}