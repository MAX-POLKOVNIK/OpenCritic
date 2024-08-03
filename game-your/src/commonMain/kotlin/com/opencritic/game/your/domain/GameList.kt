package com.opencritic.game.your.domain

data class GameList(
    val id: String,
    val posters: List<String>,
    val name: String,
    val gamesCount: Int,
    val shareLink: String,
)