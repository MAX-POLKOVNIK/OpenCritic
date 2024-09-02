package com.opencritic.news.domain

class GetArticleInteractor(
    private val repository: ArticleRepository,
) {
    suspend operator fun invoke(articleId: Long): Result<Article> =
        runCatching { repository.getArticle(articleId) }
}