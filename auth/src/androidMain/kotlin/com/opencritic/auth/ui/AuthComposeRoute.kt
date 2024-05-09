package com.opencritic.auth.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.opencritic.navigation.AuthDestination

fun NavGraphBuilder.authComposeRoute(navController: NavController) {
    composable(
        route = AuthDestination.path,
        arguments = emptyList()
    ) {
        AuthScreen(navController)
    }
}