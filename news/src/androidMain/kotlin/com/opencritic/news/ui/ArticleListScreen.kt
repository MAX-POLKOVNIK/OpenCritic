package com.opencritic.news.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.opencritic.mvvm.AppBarTitleMode
import com.opencritic.mvvm.CommonScaffoldScreen
import com.opencritic.mvvm.CommonScreen
import com.opencritic.navigation.router
import org.koin.androidx.compose.koinViewModel

@Composable
fun ArticleListScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ArticleListViewModel = koinViewModel(),
) {
    CommonScaffoldScreen(
        viewModel = viewModel,
        navController = navController,
        modifier = modifier,
        appBarTitleMode = AppBarTitleMode.Center,
    ) { content ->
        ArticleListContent(content = content)
    }
}