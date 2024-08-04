package com.opencritic.news.domain

interface ArticleRepository {
    suspend fun getArticles(skip: Int): List<ArticlePreview>
}