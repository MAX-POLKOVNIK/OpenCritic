package com.opencritic.news.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.opencritic.mvvm.CommonScaffoldScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ArticleScreen(
    articleId: Long,
    articleName: String,
    navController: NavController,
    viewModel: ArticleViewModel = koinViewModel { parametersOf(articleId, articleName) },
) {
    CommonScaffoldScreen(viewModel = viewModel, navController = navController) { content ->
        ArticleContent(content)
    }
}