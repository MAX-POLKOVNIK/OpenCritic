package com.opencritic.news.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.flow.Flow

class ArticlesDataSource(
    private val interactor: GetArticlesInteractor,
) : PagingSource<Int, ArticlePreview>() {

    override fun getRefreshKey(state: PagingState<Int, ArticlePreview>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticlePreview> {
        val pageNumber = params.key ?: 0

        println("Loading page: $pageNumber")

        val result = interactor(pageNumber * pageSize)

        return if (result.isSuccess) {
            LoadResult.Page(
                data = result.getOrThrow(),
                prevKey = if (pageNumber > 0) pageNumber - 1 else null,
                nextKey = pageNumber + 1,
            )
        } else {
            LoadResult.Error(checkNotNull(result.exceptionOrNull()))
        }
    }

    companion object {
        internal const val pageSize = 10 // it's api limitation
    }
}

fun GetArticlesInteractor.pagingFlow(): Flow<PagingData<ArticlePreview>> =
    Pager(PagingConfig(ArticlesDataSource.pageSize)) { ArticlesDataSource(this) }.flow
