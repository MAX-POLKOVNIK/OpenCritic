package com.opencritic.news.ui

import com.opencritic.games.Outlet
import com.opencritic.games.details.ui.LoadingItem
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseContentViewModel
import com.opencritic.mvvm.CommonViewModelState
import com.opencritic.navigation.ArticleRoute
import com.opencritic.navigation.OutletReviewsRoute
import com.opencritic.news.domain.ArticlePreview
import com.opencritic.news.domain.GetArticlesInteractor
import kotlinx.coroutines.launch

class ArticleListViewModel(
    private val getArticlesInteractor: GetArticlesInteractor,
    private val logger: Logger,
) : BaseContentViewModel<ArticleListContent>() {
    override fun initialState(): CommonViewModelState<ArticleListContent> =
        CommonViewModelState.loading()

    private var canLoadMore: Boolean = true
    private var skip: Int = 0

    override fun onStateInit() {
        super.onStateInit()

        loadMore()
    }

    private fun loadMore(clearList: Boolean = false) {
        if (!canLoadMore)
            return

        scope.launch {
            getArticlesInteractor(
                skip = skip,
            )
                .onSuccess { articles ->
                    logger.log("Loaded articles count: ${articles.size} --- ${state.value.content?.items?.size}")

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
                            )
                        }
                    }
                }
                .onFailure {
                    logger.log(it.toString())
                }
        }
    }

    private fun navigateToArticle(articlePreview: ArticlePreview) {
        requireRouter()
            .navigateTo(
                ArticleRoute(
                    articleId = articlePreview.id,
                    title = articlePreview.teaser,
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
}