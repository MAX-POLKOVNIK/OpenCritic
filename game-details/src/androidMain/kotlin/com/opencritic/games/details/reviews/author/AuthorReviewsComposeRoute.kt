package com.opencritic.games.details.reviews.author

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.opencritic.navigation.AuthorReviewsDestination

fun NavGraphBuilder.authorReviewsComposeRoute(navController: NavController) {
    composable(
        route = AuthorReviewsDestination.path,
        arguments = listOf(
            navArgument(AuthorReviewsDestination.AUTHOR_ID_KEY) { type = NavType.IntType },
            navArgument(AuthorReviewsDestination.AUTHOR_NAME_KEY) { type = NavType.StringType }
        )
    ) {
        val outletId = requireNotNull(it.arguments?.getInt(AuthorReviewsDestination.AUTHOR_ID_KEY))
        val outletName = requireNotNull(it.arguments?.getString(AuthorReviewsDestination.AUTHOR_NAME_KEY))

        AuthorReviewsScreen(outletId, outletName, navController)
    }
}