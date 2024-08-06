package com.opencritic.about.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.opencritic.navigation.AboutDestination

fun NavGraphBuilder.aboutComposeRoute(navController: NavController) {
    composable(
        route = AboutDestination.path,
        arguments = emptyList()
    ) {
        AboutScreen(navController)
    }
}