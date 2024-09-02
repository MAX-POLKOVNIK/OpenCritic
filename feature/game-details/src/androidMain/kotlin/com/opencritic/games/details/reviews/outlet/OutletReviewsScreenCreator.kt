package com.opencritic.games.details.reviews.outlet

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.opencritic.games.details.api.ui.OutletReviewsRoute
import com.opencritic.navigation.Route
import com.opencritic.navigation.ScreenCreator

data object OutletReviewsScreenCreator : ScreenCreator<OutletReviewsRoute.InitArgs>() {
    override val route: Route<OutletReviewsRoute.InitArgs> = OutletReviewsRoute

    @Composable
    override fun Composable(args: OutletReviewsRoute.InitArgs, navController: NavController) {
        OutletReviewsScreen(args, navController)
    }
}