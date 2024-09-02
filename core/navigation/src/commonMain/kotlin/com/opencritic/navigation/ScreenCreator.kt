package com.opencritic.navigation

expect abstract class ScreenCreator<InitArgs : Any>() {
    abstract val route: Route<InitArgs>
}

fun <InitArgs : Any> ScreenCreator<InitArgs>.isForRoute(route: Route<*>): Boolean =
    route == this.route