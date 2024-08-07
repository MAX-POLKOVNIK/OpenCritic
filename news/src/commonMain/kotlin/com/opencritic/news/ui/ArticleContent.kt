package com.opencritic.news.ui

import com.opencritic.mvvm.ActionedScreenContent
import com.opencritic.resources.images.IconResource
import com.opencritic.resources.images.Icons
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource

data class ArticleContent(
    val bannerImageUrl: String,
    val title: String,
    val isOutletVisible: Boolean,
    val outletTitleText: TextSource,
    val outletText: String,
    val writtenBy: TextSource,
    val publishedDateText: TextSource,
    val isGameDiscussedVisible: Boolean,
    val gamesTitleDiscussedText: TextSource,
    val gamesDiscussedText: String,
    val htmlToRender: String,
    val isSeeFullArticleVisible: Boolean,
    val seeFullArticleText: TextSource,
    val onOutletClick: () -> Unit,
    val onGameClick: () -> Unit,
    val onSeeFullArticleClick: () -> Unit,
    override val isActionVisible: Boolean,
    override val actionIconResource: IconResource,
    override val onAction: () -> Unit,
) : ActionedScreenContent

@Suppress("FunctionName")
fun ArticleContent_PreviewData(): ArticleContent =
    ArticleContent(
        bannerImageUrl = "https://img.opencritic.com/article/bYzduDgClgitYvnjwot02DfkCG66eUSCfF3aUqKNwqlXtjcqE9exG8X6O1Vmjb.jpg",
        title = "Nintendo Highlights 7 Big Upcoming Switch Exclusive Games ",
        isOutletVisible = true,
        outletTitleText = "From".asTextSource(),
        outletText = "Game Rant",
        writtenBy = "Written by Dalton Cooper".asTextSource(),
        publishedDateText = "August 4, 2024".asTextSource(),
        isGameDiscussedVisible = true,
        gamesTitleDiscussedText = "Games discussed:".asTextSource(),
        gamesDiscussedText = "Nintendo Switch Sports",
        isActionVisible = true,
        actionIconResource = Icons.share,
        htmlToRender = "Hello",
        isSeeFullArticleVisible = true,
        seeFullArticleText = "See full article at Game Rant".asTextSource(),
        onAction = {},
        onOutletClick = {},
        onGameClick = {},
        onSeeFullArticleClick = {}
    )