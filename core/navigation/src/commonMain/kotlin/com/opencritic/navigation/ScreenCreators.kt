package com.opencritic.navigation

data class ScreenCreators(val list: List<ScreenCreator<*>>) {
    constructor(vararg creator: ScreenCreator<*>) : this(creator.toList())

    @Suppress("UNCHECKED_CAST")
    fun <InitArgs : Any> find(
        route: Route<InitArgs>,
    ): ScreenCreator<InitArgs> {
        val creator = list.find { it.isForRoute(route) }

        checkNotNull(creator) { "Can't find creator for $route in $this" }

        return creator as ScreenCreator<InitArgs>
    }

    companion object
}