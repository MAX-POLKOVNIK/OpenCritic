package com.opencritic.about.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.opencritic.about.api.ui.AboutRoute
import com.opencritic.navigation.Route
import com.opencritic.navigation.ScreenCreator

data object AboutScreenCreator : ScreenCreator<AboutRoute.InitArgs>() {
    override val route: Route<AboutRoute.InitArgs> = AboutRoute

    @Composable
    override fun Composable(args: AboutRoute.InitArgs, navController: NavController) {
        AboutScreen(args, navController)
    }
}