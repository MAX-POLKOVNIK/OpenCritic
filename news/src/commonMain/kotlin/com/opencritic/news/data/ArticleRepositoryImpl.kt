package com.opencritic.news.data

import com.opencritic.api.OpenCriticsApi
import com.opencritic.api.dto.article.ArticlePreviewDto
import com.opencritic.games.Author
import com.opencritic.games.Outlet
import com.opencritic.news.domain.ArticlePreview
import com.opencritic.news.domain.ArticleRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

internal class ArticleRepositoryImpl(
    private val openCriticsApi: OpenCriticsApi,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ArticleRepository {
    override suspend fun getArticles(skip: Int): List<ArticlePreview> =
        withContext(defaultDispatcher) {
            openCriticsApi.getArticlePreviews(skip)
                .map { ArticlePreview(it) }
        }

    private fun ArticlePreview(dto: ArticlePreviewDto): ArticlePreview =
        ArticlePreview(
            id = dto.id,
            bannerUrl = dto.imageV2.sm.prefixedArticleImageUrl(),
            teaser = dto.title,
            description = dto.description,
            outlet = dto.outlet
                ?.let {
                    Outlet(
                        id = it.id,
                        name = it.name,
                        imageUrl = "",
                        isContributor = false
                    )
                },
            author =
                if (dto.type == "SYNDICATED") Author(id = -1, name = dto.syndicatedAuthor ?: "")
                else dto.authors.first().let { Author(id = it.id, name = it.name) },
            publicationDate = dto.publishedDate.toLocalDateTime(TimeZone.currentSystemDefault()).date,
        )

    private fun String.prefixedArticleImageUrl(): String =
        when {
            isEmpty() -> ""
            startsWith("//") -> "https:$this"
            else -> "https://img.opencritic.com$this"
        }
}