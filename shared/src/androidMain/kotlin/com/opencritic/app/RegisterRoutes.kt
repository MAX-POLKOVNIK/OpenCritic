package com.opencritic.app

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.opencritic.main.MainScreen
import com.opencritic.navigation.ScreenCreator
import com.opencritic.navigation.ScreenCreators
import com.opencritic.navigation.composable

fun registerRoutes(navController: NavController): NavGraphBuilder.() -> Unit = {
    composable("main") { MainScreen(navController) }

    ScreenCreators.registered().let { creators ->
        creators.list.forEach { creator ->
            composable(creator.route) { args: Any ->
                @Suppress("UNCHECKED_CAST")
                (creator as ScreenCreator<Any>).Composable(args, navController)
            }
        }
    }
}