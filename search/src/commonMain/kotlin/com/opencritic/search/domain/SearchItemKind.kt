package com.opencritic.search.domain

enum class SearchItemKind {
    Game,
    Critic,
    Outlet,
}

fun SearchItemKind(string: String): SearchItemKind =
    when (string) {
        "critic" -> SearchItemKind.Critic
        "game" -> SearchItemKind.Game
        "outlet" -> SearchItemKind.Outlet
        else -> error("$string is unknown")
    }