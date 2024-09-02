package com.opencritic.navigation

expect interface Route<InitArgs : Any> {
    val screenName: String
}

expect inline fun <reified InitArgs : Any> route(
    screenName: String,
): Route<InitArgs>