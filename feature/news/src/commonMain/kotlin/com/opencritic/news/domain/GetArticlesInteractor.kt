package com.opencritic.news.domain

import com.opencritic.remote.images.ImagePreloader
import com.opencritic.remote.images.load

class GetArticlesInteractor(
    private val articleRepository: ArticleRepository,
    private val imagePreloader: ImagePreloader,
) {
    suspend operator fun invoke(skip: Int = 0): Result<List<ArticlePreview>> =
        runCatching {
            articleRepository.getArticles(skip)
                .also { imagePreloader.load(it) }
        }
}