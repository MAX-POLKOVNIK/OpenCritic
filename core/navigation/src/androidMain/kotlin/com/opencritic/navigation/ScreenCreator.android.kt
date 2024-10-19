package com.opencritic.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

actual abstract class ScreenCreator<InitArgs : Any> {
    actual abstract val route: Route<InitArgs>

    @Composable
    abstract fun Composable(args: InitArgs, navController: NavController)
}