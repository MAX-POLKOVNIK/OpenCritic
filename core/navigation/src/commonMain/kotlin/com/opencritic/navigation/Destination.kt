package com.opencritic.navigation

data class Destination<InitArgs : Any>(
    val route: Route<InitArgs>,
    val args: InitArgs,
)