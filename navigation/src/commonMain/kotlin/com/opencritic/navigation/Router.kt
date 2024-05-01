package com.opencritic.navigation

interface Router {
    fun navigateTo(route: Route)
    fun navigateBack()
    fun popToRoot()
}