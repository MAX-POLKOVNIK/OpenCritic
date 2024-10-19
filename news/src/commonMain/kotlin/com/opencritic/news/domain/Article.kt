package com.opencritic.news.domain

import com.opencritic.games.Author
import com.opencritic.games.Outlet
import kotlinx.datetime.LocalDate

data class Article(
    val id: Long,
    val bannerUrl: String,
    val teaser: String,
    val description: String,
    val outlet: Outlet?,
    val author: Author,
    val publicationDate: LocalDate,
    val html: String,
    val originalUrl: String?,
    val relatedGames: List<ArticleGame>,
)