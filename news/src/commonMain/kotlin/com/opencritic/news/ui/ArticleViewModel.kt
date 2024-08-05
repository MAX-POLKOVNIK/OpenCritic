package com.opencritic.news.ui

import com.opencritic.games.Outlet
import com.opencritic.mvvm.BaseContentViewModel
import com.opencritic.mvvm.CommonViewModelState
import com.opencritic.navigation.GameDetailsRoute
import com.opencritic.navigation.LinkShareRoute
import com.opencritic.navigation.OutletReviewsRoute
import com.opencritic.navigation.UrlRoute
import com.opencritic.news.domain.Article
import com.opencritic.news.domain.ArticleGame
import com.opencritic.news.domain.GetArticleInteractor
import com.opencritic.resources.images.Icons
import com.opencritic.resources.text.DateTextSource
import com.opencritic.resources.text.asTextSource
import com.opencritic.resources.text.format
import kotlinx.coroutines.launch

class ArticleViewModel(
    private val articleId: Long,
    private val title: String,
    private val getArticleInteractor: GetArticleInteractor,
) : BaseContentViewModel<ArticleContent>() {
    override fun initialState(): CommonViewModelState<ArticleContent> =
        CommonViewModelState.loading(title = title.asTextSource())

    override fun onStateInit() {
        super.onStateInit()

        scope.launch {
            getArticleInteractor(articleId)
                .onFailure {
                    error(it)
                }
                .onSuccess { article ->
                    setContent {
                        ArticleContent(
                            bannerImageUrl = article.bannerUrl,
                            title = article.teaser,
                            isOutletVisible = article.outlet != null,
                            outletTitleText = "From".asTextSource(),
                            outletText = article.outlet?.name ?: "",
                            writtenBy = "Written by ${article.author.name}".asTextSource(),
                            publishedDateText = article.publicationDate format DateTextSource.Format.Long,
                            isGameDiscussedVisible = article.relatedGames.isNotEmpty(),
                            gamesTitleDiscussedText = "Games discussed:".asTextSource(),
                            gamesDiscussedText = article.relatedGames.firstOrNull()?.name ?: "",
                            isShareVisible = true,
                            shareIconResource = Icons.share,
                            htmlToRender = article.html.clearLinks().removeSeeFullString(),
                            isSeeFullArticleVisible = article.originalUrl != null,
                            seeFullArticleText = "See full article at ${article.outlet?.name}".asTextSource(),
                            onShareClick = { navigateToShare(article) },
                            onOutletClick = { navigateToOutlet(article.outlet) },
                            onGameClick = { navigateToGame(article.relatedGames.firstOrNull()) },
                            onSeeFullArticleClick = { navigateToFullArticle(article.originalUrl) },
                        )
                    }
                }
        }
    }

    private fun String.clearLinks(): String {
        val start1 = indexOf("<a href")

        if (start1 == -1)
            return replace("</a>", "")

        val end1 = indexOf(">", startIndex = start1 + 1) + 1

        val cleared = removeRange(start1, end1)

        val s = cleared.clearLinks()

        return s
    }

    private fun String.removeSeeFullString(): String {
        val start1 = indexOf("See full article at")

        return substring(0, start1)
    }

    private fun navigateToGame(articleGame: ArticleGame?) {
        if (articleGame == null) return

        requireRouter()
            .navigateTo(
                GameDetailsRoute(
                    gameId = articleGame.id,
                    gameName = articleGame.name
                )
            )
    }

    private fun navigateToOutlet(outlet: Outlet?) {
        if (outlet == null) return

        requireRouter()
            .navigateTo(
                OutletReviewsRoute(
                    outletId = outlet.id,
                    outletName = outlet.name,
                )
            )
    }

    private fun navigateToFullArticle(url: String?) {
        if (url.isNullOrBlank()) return

        requireRouter()
            .navigateTo(
                UrlRoute(url)
            )
    }

    private fun navigateToShare(article: Article) {
        val title = Regex("[^A-Za-z0-9 ]")
            .replace(article.teaser, "-")
            .lowercase()
            .replace(" ", "-")

        val link = "https://opencritic.com/news/${article.id}/${title}"

        requireRouter()
            .navigateTo(
                LinkShareRoute(link)
            )
    }
}