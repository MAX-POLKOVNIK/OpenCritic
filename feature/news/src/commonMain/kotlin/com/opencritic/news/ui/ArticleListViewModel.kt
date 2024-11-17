package com.opencritic.news.ui

import androidx.paging.ItemSnapshotList
import androidx.paging.PagingData
import androidx.paging.PagingDataEvent
import androidx.paging.PagingDataPresenter
import androidx.paging.cachedIn
import com.opencritic.games.details.api.ui.OutletReviewsRoute
import com.opencritic.mvvm.BaseContentViewModel
import com.opencritic.mvvm.CommonViewModelState
import com.opencritic.news.api.ArticleRoute
import com.opencritic.news.domain.ArticlePreview
import com.opencritic.news.domain.GetArticlesInteractor
import com.opencritic.news.domain.pagingFlow
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.asTextSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class ArticleListViewModel(
    private val getArticlesInteractor: GetArticlesInteractor,
) : BaseContentViewModel<ArticleListContent>() {
    override fun initialState(): CommonViewModelState<ArticleListContent> =
        CommonViewModelState.loading(title = StringRes.str_tab_news.asTextSource())

    private val pullToRefreshFlow = MutableStateFlow(0)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val articlesFlow: Flow<PagingData<ArticlePreview>> =
        pullToRefreshFlow.flatMapLatest {
            getArticlesInteractor.pagingFlow().cachedIn(scope)
        }

    private val articlesPagingDataPresenter = object : PagingDataPresenter<ArticlePreview>() {
        override suspend fun presentPagingDataEvent(event: PagingDataEvent<ArticlePreview>) {
            updateSnapshot()
        }
    }

    override fun onStateInit() {
        super.onStateInit()

        scope.launch {
            articlesFlow.collectLatest {
                articlesPagingDataPresenter.collectFrom(it)
            }
        }

        setContent {
            ArticleListContent(
                items = ItemSnapshotList(0, 0, emptyList()),
                isRefreshing = false,
                isLoadingItemVisible = true,
                onRefresh = { onRefresh() },
                onRefreshRequested = ::onRefreshRequested,
                getItemAt = ::getElement
            )
        }
    }

    private fun getElement(index: Int): ArticleListItem? =
        articlesPagingDataPresenter[index]
            ?.let {
                ArticleListItem(
                    articlePreview = it,
                    onClick = ::navigateToArticle,
                    onOutletClick = ::navigateToOutlet,
                )
            }

    private suspend fun onRefresh() {
        onRefreshRequested()

        // Need to think how to bound paging and suspend
        delay(1.seconds)
    }

    private fun onRefreshRequested() {
        updateContentIfSet {
            copy(isRefreshing = true)
        }
        pullToRefreshFlow.update { it.inc() }
    }

    private fun updateSnapshot() {
        hideLoading()

        updateContentIfSet {
            copy(
                isRefreshing = false,
                items = articlesPagingDataPresenter.snapshot()
            )
        }
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