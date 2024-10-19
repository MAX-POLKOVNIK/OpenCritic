package com.opencritic.news.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.opencritic.mvvm.CommonScaffoldScreen
import com.opencritic.news.api.ArticleRoute
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ArticleScreen(
    args: ArticleRoute.InitArgs,
    navController: NavController,
    viewModel: ArticleViewModel = koinViewModel { parametersOf(args) },
) {
    CommonScaffoldScreen(viewModel = viewModel, navController = navController) { content ->
        ArticleContent(content)
    }
}