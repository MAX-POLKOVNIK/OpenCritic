package com.opencritic.games.details.reviews.outlet

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.opencritic.navigation.OutletReviewsDestination

fun NavGraphBuilder.outletReviewsComposeRoute(navController: NavController) {
    composable(
        route = OutletReviewsDestination.path,
        arguments = listOf(
            navArgument(OutletReviewsDestination.OUTLET_ID_KEY) { type = NavType.IntType },
            navArgument(OutletReviewsDestination.OUTLET_NAME_KEY) { type = NavType.StringType }
        )
    ) {
        val outletId = requireNotNull(it.arguments?.getInt(OutletReviewsDestination.OUTLET_ID_KEY))
        val outletName = requireNotNull(it.arguments?.getString(OutletReviewsDestination.OUTLET_NAME_KEY))

        OutletReviewsScreen(outletId, outletName, navController)
    }
}