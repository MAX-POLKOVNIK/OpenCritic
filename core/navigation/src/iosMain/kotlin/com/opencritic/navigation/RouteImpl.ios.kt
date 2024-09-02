package com.opencritic.navigation

internal class RouteImpl<InitArgs : Any>(
    override val screenName: String,
) : Route<InitArgs>