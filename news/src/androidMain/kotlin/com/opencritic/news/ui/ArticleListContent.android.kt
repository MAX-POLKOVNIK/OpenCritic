package com.opencritic.news.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.opencritic.games.details.LoadingItem
import com.opencritic.resources.defaultPadding

@Composable
fun ArticleListContent(
    content: ArticleListContent,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        contentPadding = PaddingValues(defaultPadding),
        verticalArrangement = Arrangement.spacedBy(defaultPadding),
        modifier = modifier,
    ) {
        content.items.forEach {
            item(key = it.id) {
                ArticleListItem(item = it)
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
}

@Preview
@Composable
fun ArticleListContent_Preview() {
    ArticleListContent(
        content = ArticleListContent_PreviewData()
    )
}