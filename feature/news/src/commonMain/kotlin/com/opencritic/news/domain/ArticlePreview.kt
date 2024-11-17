package com.opencritic.news.domain

import com.opencritic.games.Author
import com.opencritic.games.Outlet
import com.opencritic.remote.images.ImagePreloadable
import com.opencritic.remote.images.ImageUrl
import com.opencritic.remote.images.preloadableImageListOf
import kotlinx.datetime.LocalDate

data class ArticlePreview(
    val id: Long,
    val bannerUrl: String,
    val teaser: String,
    val description: String,
    val outlet: Outlet?,
    val author: Author,
    val publicationDate: LocalDate,
) : ImagePreloadable {
    override val preloadableImageUrls: List<ImageUrl>
        get() = preloadableImageListOf(bannerUrl)
}