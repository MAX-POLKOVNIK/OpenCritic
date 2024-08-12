package com.opencritic.games

data class Platform(
    val name: String,
    val code: String,
) {
    val isNextGenAvailable: Boolean =
        code == "PS5" || code == "XBXS"
}