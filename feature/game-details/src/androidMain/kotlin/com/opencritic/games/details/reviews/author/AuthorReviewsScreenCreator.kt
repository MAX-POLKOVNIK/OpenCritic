package com.opencritic.games.details.reviews.author

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.opencritic.games.details.api.ui.AuthorReviewsRoute
import com.opencritic.navigation.Route
import com.opencritic.navigation.ScreenCreator

data object AuthorReviewsScreenCreator : ScreenCreator<AuthorReviewsRoute.InitArgs>() {
    override val route: Route<AuthorReviewsRoute.InitArgs> = AuthorReviewsRoute

    @Composable
    override fun Composable(args: AuthorReviewsRoute.InitArgs, navController: NavController) {
        AuthorReviewsScreen(args, navController)
    }
}