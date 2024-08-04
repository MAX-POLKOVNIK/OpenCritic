package com.opencritic.news.domain

class GetArticlesInteractor(
    private val articleRepository: ArticleRepository,
) {
    suspend operator fun invoke(skip: Int = 0): Result<List<ArticlePreview>> =
        runCatching { articleRepository.getArticles(skip) }
}