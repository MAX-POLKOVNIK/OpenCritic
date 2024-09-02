package com.opencritic.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

fun <InitArgs : Any> NavGraphBuilder.composable(
    route: Route<InitArgs>,
    content: @Composable AnimatedContentScope.(InitArgs) -> Unit
) {
    composable(
        route = route.registrationPath(),
        arguments = listOf(
            navArgument(argsKey) { type = NavType.StringType },
        ),
    ) {
        val serialized = requireNotNull(it.arguments?.getString(argsKey))
        val deserialized = route.deserialize(serialized)

        content(deserialized)
    }
}

