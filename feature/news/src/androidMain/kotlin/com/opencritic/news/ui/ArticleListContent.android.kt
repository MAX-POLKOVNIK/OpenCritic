package com.opencritic.news.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.opencritic.mvvm.rememberForeverLazyListState
import com.opencritic.resources.defaultPadding
import com.opencritic.resources.smallPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleListContent(
    content: ArticleListContent,
    modifier: Modifier = Modifier,
) {
    PullToRefreshBox(
        isRefreshing = content.isRefreshing,
        onRefresh = { content.onRefreshRequested() },
        modifier = modifier
    ) {
        LazyColumn(
            state = rememberForeverLazyListState(key = "ArticleListContent"),
            contentPadding = PaddingValues(defaultPadding),
            modifier = modifier,
        ) {
            content.itemIndices.forEach { index ->
                item(key = index) {
                    content.getItemAt(index)?.let {
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