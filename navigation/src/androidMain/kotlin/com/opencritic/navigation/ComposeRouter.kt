package com.opencritic.navigation

import androidx.navigation.NavController

class ComposeRouter(
    private val navController: NavController,
) : Router {
    override fun navigateTo(route: Route) {
        navController.navigate(route.id)
    }

    override fun navigateBack() {
        navController.popBackStack()
    }

    override fun popToRoot() {
        TODO("Not yet implemented")
    }
}

fun NavController.router(): ComposeRouter =
    ComposeRouter(this)