package com.opencritic.games

data class Game(
    val id: Long,
    val name: String,
    val firstReleaseDate: String,
    val tier: Tier,
    val score: Int,
    val imageUrl: String,
)