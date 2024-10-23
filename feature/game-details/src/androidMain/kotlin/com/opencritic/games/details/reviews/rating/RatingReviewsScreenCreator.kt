package com.opencritic.games.details.reviews.rating

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.opencritic.games.details.api.ui.RatingReviewsRoute
import com.opencritic.navigation.Route
import com.opencritic.navigation.ScreenCreator

data object RatingReviewsScreenCreator : ScreenCreator<RatingReviewsRoute.InitArgs>() {
    override val route: Route<RatingReviewsRoute.InitArgs> = RatingReviewsRoute

    @Composable
    override fun Composable(args: RatingReviewsRoute.InitArgs, navController: NavController) {
        RatingReviewsScreen(args, navController)
    }
}