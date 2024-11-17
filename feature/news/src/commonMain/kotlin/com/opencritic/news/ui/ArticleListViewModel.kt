package com.opencritic.news.ui

import com.opencritic.games.details.api.ui.OutletReviewsRoute
import com.opencritic.games.details.ui.LoadingItem
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseContentViewModel
import com.opencritic.mvvm.CommonViewModelState
import com.opencritic.news.api.ArticleRoute
import com.opencritic.news.domain.GetArticlesInteractor
import com.opencritic.remote.images.ImagePreloader
import com.opencritic.remote.images.load
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.asTextSource
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch

class ArticleListViewModel(
    private val getArticlesInteractor: GetArticlesInteractor,
    private val logger: Logger,
    private val imagePreloader: ImagePreloader,
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
                imagePreloader.load(articles)

                hideLoading()

                val newListItems = articles.map {
                    ArticleListItem(
                        articlePreview = it,
                        onClick = ::navigateToArticle,
                        onOutletClick = ::navigateToOutlet,
                    )
                }

                if (isContentSet) {
                    updateContentIfSet {
                        val items = ((if (clearList) emptyList() else items) + newListItems)
                            .distinctBy { it.id }

                        skip = items.size

                        copy(
                            isRefreshing = false,
                            items = items.toImmutableList()
                        )
                    }
                } else {
                    setContent {
                        skip = newListItems.size

                        ArticleListContent(
                            items = newListItems.toImmutableList(),
                            isRefreshing = false,
                            isLoadingItemVisible = true,
                            loadingItem = LoadingItem,
                            onLoadMore = ::loadMore,
                            onRefresh = { refresh() },
                            onRefreshRequested = ::onRefreshRequested
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

    private fun onRefreshRequested() {
        scope.launch {
            updateContentIfSet {
                copy(isRefreshing = true)
            }

            refresh()
        }
    }

    private suspend fun refresh() {
        loadMore(clearList = true)
    }

    private fun navigateToArticle(item: ArticleListItem) {
        ArticleRoute.navigate(
            ArticleRoute.InitArgs(item.id, item.title)
        )
    }

    private fun navigateToOutlet(item: ArticleListItem) {
        val id = item.outletId ?: return

        OutletReviewsRoute.navigate(
            OutletReviewsRoute.InitArgs(id, item.outletText)
        )
    }
}