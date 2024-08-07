package com.opencritic.halloffame.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.opencritic.navigation.HallOfFameDestination

fun NavGraphBuilder.hallsOfFameComposeRoute(navController: NavController) {
    composable(
        route = HallOfFameDestination.path,
        arguments = emptyList()
    ) {
        HallsOfFameScreen(navController)
    }
}