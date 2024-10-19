package com.opencritic.game.your.domain

data class GameList(
    val id: String,
    val name: String,
    val gamesCount: Int,
    val shareLink: String,
    val games: List<GameInList>,
) {
    val posters: List<String> =
        games.map { it.posterUrl }
}