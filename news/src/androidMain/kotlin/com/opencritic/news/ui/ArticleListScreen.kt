package com.opencritic.news.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.opencritic.mvvm.CommonScreen
import com.opencritic.navigation.router
import org.koin.androidx.compose.koinViewModel

@Composable
fun ArticleListScreen(
    navController: NavController,
    viewModel: ArticleListViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val router = navController.router()

    viewModel.setRouter(router)

    CommonScreen(state = state) { content, modifier ->
        ArticleListContent(content = content, modifier = modifier)
    }
}