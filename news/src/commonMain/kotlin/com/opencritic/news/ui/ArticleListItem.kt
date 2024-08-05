package com.opencritic.news.ui

import com.opencritic.mvvm.ListItem
import com.opencritic.news.domain.ArticlePreview
import com.opencritic.resources.text.DateTextSource
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource
import com.opencritic.resources.text.format

data class ArticleListItem(
    override val id: Long,
    val bannerImageUrl: String,
    val title: String,
    val summary: String,
    val isOutletVisible: Boolean,
    val outletTitleText: TextSource,
    val outletText: String,
    val writtenBy: TextSource,
    val publishedDateText: TextSource,
    val readMoreText: TextSource,
    val onReadMoreClick: () -> Unit,
    val onOutletClick: () -> Unit,
) : ListItem<Long>

fun ArticleListItem(
    articlePreview: ArticlePreview,
    onClick: () -> Unit,
    onOutletClick: () -> Unit,
): ArticleListItem = ArticleListItem(
    id = articlePreview.id,
    bannerImageUrl = articlePreview.bannerUrl,
    title = articlePreview.teaser,
    summary = articlePreview.description,
    isOutletVisible = articlePreview.outlet != null,
    outletTitleText = "From".asTextSource(),
    outletText = articlePreview.outlet?.name ?: "",
    writtenBy = "Written by ${articlePreview.author.name}".asTextSource(),
    publishedDateText = articlePreview.publicationDate format DateTextSource.Format.Long,
    readMoreText = "Read more".asTextSource(),
    onReadMoreClick = onClick,
    onOutletClick = onOutletClick,
)

@Suppress("FunctionName")
fun ArticleListItem_PreviewData(): ArticleListItem =
    ArticleListItem(
        id = 1,
        bannerImageUrl = "https://opencritic.com/news/3612/xbox-game-roadmap-highlights-major-games-coming-in-2024-and-2025",
        title = "Xbox Game Roadmap Highlights Major Games Coming in 2024 and 2025",
        summary = "Microsoft releases a roadmap showcasing major new Xbox games coming in 2024 and 2025.",
        isOutletVisible = true,
        outletTitleText = "From".asTextSource(),
        outletText = "Game Rant",
        writtenBy = "Written by Dalton Cooper".asTextSource(),
        publishedDateText = "August 4, 2024".asTextSource(),
        readMoreText = "Read more".asTextSource(),
        onReadMoreClick = {},
        onOutletClick = {},
    )