package com.opencritic.news.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.opencritic.navigation.Route
import com.opencritic.navigation.ScreenCreator
import com.opencritic.news.api.ArticleListRoute

data object ArticleListScreenCreator : ScreenCreator<ArticleListRoute.InitArgs>() {
    override val route: Route<ArticleListRoute.InitArgs> = ArticleListRoute

    @Composable
    override fun Composable(args: ArticleListRoute.InitArgs, navController: NavController) {
        ArticleListScreen(navController)
    }
}