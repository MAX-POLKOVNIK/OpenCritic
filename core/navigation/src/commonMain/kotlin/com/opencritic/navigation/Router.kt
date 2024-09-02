package com.opencritic.navigation

interface Router {
    fun <InitArgs : Any> navigateTo(destination: Destination<InitArgs>)
    fun navigateBack()
    fun popToRoot()
}