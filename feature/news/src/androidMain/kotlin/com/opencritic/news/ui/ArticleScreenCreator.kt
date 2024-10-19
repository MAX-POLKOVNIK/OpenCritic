package com.opencritic.news.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.opencritic.navigation.Route
import com.opencritic.navigation.ScreenCreator
import com.opencritic.news.api.ArticleRoute

data object ArticleScreenCreator : ScreenCreator<ArticleRoute.InitArgs>() {
    override val route: Route<ArticleRoute.InitArgs> = ArticleRoute

    @Composable
    override fun Composable(args: ArticleRoute.InitArgs, navController: NavController) {
        ArticleScreen(args, navController)
    }
}