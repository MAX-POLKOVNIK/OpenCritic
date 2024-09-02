package com.opencritic.news.ui

import com.opencritic.games.Outlet
import com.opencritic.games.details.api.ui.OutletReviewsRoute
import com.opencritic.games.details.ui.LoadingItem
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseContentViewModel
import com.opencritic.mvvm.CommonViewModelState
import com.opencritic.news.api.ArticleRoute
import com.opencritic.news.domain.ArticlePreview
import com.opencritic.news.domain.GetArticlesInteractor
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.asTextSource
import kotlinx.coroutines.launch

class ArticleListViewModel(
    private val getArticlesInteractor: GetArticlesInteractor,
    private val logger: Logger,
) : BaseContentViewModel<ArticleListContent>() {
    override fun initialState(): CommonViewModelState<ArticleListContent> =
        CommonViewModelState.loading(title = StringRes.str_tab_news.asTextSource())

    private var canLoadMore: Boolean = true
    private var skip: Int = 0

    override fun onStateInit() {
        super.onStateInit()

        loadMore()
    }

    private suspend fun loadMore(clearList: Boolean = false) {
        if (!canLoadMore)
            return

        getArticlesInteractor(
            skip = if (clearList) 0 else skip,
        )
            .onSuccess { articles ->
                logger.log("Loaded articles count: ${articles.size} --- ${state.value.content?.items?.size}")
                logger.log("Loaded articles: ${articles.map { it.id }}")

                hideLoading()

                val newListItems = articles.map {
                    ArticleListItem(
                        articlePreview = it,
                        onClick = { navigateToArticle(it) },
                        onOutletClick = { navigateToOutlet(it.outlet) }
                    )
                }

                if (isContentSet) {
                    updateContentIfSet {
                        val items = ((if (clearList) emptyList() else items) + newListItems)
                            .distinctBy { it.id }

                        skip = items.size

                        copy(items = items)
                    }
                } else {
                    setContent {
                        skip = newListItems.size

                        ArticleListContent(
                            items = newListItems,
                            isLoadingItemVisible = true,
                            loadingItem = LoadingItem,
                            onLoadMore = { loadMore() },
                            onRefresh = { refresh() }
                        )
                    }
                }
            }
            .onFailure {
                logger.log(it.toString())
            }
    }

    private fun loadMore() {
        scope.launch {
            loadMore(clearList = false)
        }
    }

    private suspend fun refresh() {
        loadMore(clearList = true)
    }

    private fun navigateToArticle(articlePreview: ArticlePreview) {
        ArticleRoute.navigate(
            ArticleRoute.InitArgs(articlePreview.id, articlePreview.teaser)
        )
    }

    private fun navigateToOutlet(outlet: Outlet?) {
        if (outlet == null) return

        OutletReviewsRoute.navigate(
            OutletReviewsRoute.InitArgs(outlet.id, outlet.name)
        )
    }
}