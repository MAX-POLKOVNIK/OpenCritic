package com.opencritic.navigation

actual interface Route<InitArgs : Any> {
    actual val screenName: String
}

actual inline fun <reified InitArgs : Any> route(
    screenName: String,
): Route<InitArgs> =
    simpleRoute(
        screenName = screenName,
    )

fun <InitArgs : Any> simpleRoute(
    screenName: String,
): Route<InitArgs> =
    RouteImpl(
        screenName = screenName,
    )