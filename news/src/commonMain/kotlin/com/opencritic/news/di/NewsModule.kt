package com.opencritic.news.di

import com.opencritic.mvvm.viewModelOf
import com.opencritic.news.data.ArticleRepositoryImpl
import com.opencritic.news.domain.ArticleRepository
import com.opencritic.news.domain.GetArticlesInteractor
import com.opencritic.news.ui.ArticleListViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val newsModule = module {
    single<ArticleRepository> { ArticleRepositoryImpl(get()) }
    singleOf(::GetArticlesInteractor)

    viewModelOf(::ArticleListViewModel)
}