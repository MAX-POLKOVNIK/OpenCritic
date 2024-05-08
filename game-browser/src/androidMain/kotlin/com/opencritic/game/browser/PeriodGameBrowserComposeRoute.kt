package com.opencritic.game.browser

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.opencritic.navigation.PeriodGameBrowserDestination

fun NavGraphBuilder.periodGameBrowserComposeRoute(navController: NavController) {
    composable(
        route = PeriodGameBrowserDestination.path,
        arguments = listOf(
            navArgument(PeriodGameBrowserDestination.PERIOD_KEY) { type = NavType.StringType },
        )
    ) {
        val periodString = requireNotNull(it.arguments?.getString(PeriodGameBrowserDestination.PERIOD_KEY))
        val period = PeriodGameBrowserDestination.Period.entries.first { p -> p.name == periodString }

        PeriodGameBrowserScreen(period, navController)
    }
}