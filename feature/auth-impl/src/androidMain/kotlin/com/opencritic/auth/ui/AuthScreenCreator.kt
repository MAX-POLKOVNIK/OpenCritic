package com.opencritic.auth.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.opencritic.auth.api.ui.AuthRoute
import com.opencritic.navigation.Route
import com.opencritic.navigation.ScreenCreator

data object AuthScreenCreator : ScreenCreator<AuthRoute.InitArgs>() {
    override val route: Route<AuthRoute.InitArgs> = AuthRoute

    @Composable
    override fun Composable(args: AuthRoute.InitArgs, navController: NavController) {
        AuthScreen(navController)
    }
}