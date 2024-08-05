package com.opencritic.news.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.opencritic.navigation.ArticleDestination

fun NavGraphBuilder.articleComposeRoute(navController: NavController) {
    composable(
        route = ArticleDestination.path,
        arguments = listOf(
            navArgument(ArticleDestination.ARTICLE_ID_KEY) { type = NavType.LongType },
            navArgument(ArticleDestination.ARTICLE_NAME_KEY) { type = NavType.StringType }
        )
    ) {
        val articleId = requireNotNull(it.arguments?.getLong(ArticleDestination.ARTICLE_ID_KEY))
        val articleName = requireNotNull(it.arguments?.getString(ArticleDestination.ARTICLE_NAME_KEY))

        ArticleScreen(articleId, articleName, navController)
    }
}