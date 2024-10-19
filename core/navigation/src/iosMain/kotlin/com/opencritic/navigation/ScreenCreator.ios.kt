package com.opencritic.navigation

actual abstract class ScreenCreator<InitArgs : Any> {
    actual abstract val route: Route<InitArgs>

    @Suppress("unused")
    abstract fun view(args: InitArgs): Any
}