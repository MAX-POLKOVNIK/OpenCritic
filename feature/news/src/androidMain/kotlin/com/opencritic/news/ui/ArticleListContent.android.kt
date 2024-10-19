package com.opencritic.news.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import com.opencritic.games.details.LoadingItem
import com.opencritic.mvvm.rememberForeverLazyListState
import com.opencritic.resources.defaultPadding
import com.opencritic.resources.smallPadding
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleListContent(
    content: ArticleListContent,
    modifier: Modifier = Modifier,
) {
    val pullRefreshState = rememberPullToRefreshState()

    Box(modifier.nestedScroll(pullRefreshState.nestedScrollConnection)) {
        LazyColumn(
            state = rememberForeverLazyListState(key = "ArticleListContent"),
            contentPadding = PaddingValues(defaultPadding),
            modifier = modifier,
        ) {
            content.items.forEach {
                item(key = it.id) {
                    Column {
                        ArticleListItem(
                            item = it,
                            modifier = Modifier
                                .padding(vertical = smallPadding)
                        )
                        HorizontalDivider()
                    }
                }
            }

            if (content.isLoadingItemVisible) {
                item {
                    LoadingItem(
                        item = content.loadingItem,
                    )
                    LaunchedEffect(Unit) {
                        content.onLoadMore()
                    }
                }
            }
        }

        if (pullRefreshState.progress > 0 || pullRefreshState.isRefreshing) {
            PullToRefreshContainer(
                modifier = Modifier.align(Alignment.TopCenter),
                state = pullRefreshState,
            )
            LaunchedEffect(key1 = Unit) {
                content.onRefresh()
                delay(1.seconds)
                pullRefreshState.endRefresh()
            }
        }
    }
}

@Preview
@Composable
fun ArticleListContent_Preview() {
    ArticleListContent(
        content = ArticleListContent_PreviewData()
    )
}