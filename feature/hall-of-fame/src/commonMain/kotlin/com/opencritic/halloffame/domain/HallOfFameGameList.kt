package com.opencritic.halloffame.domain

data class HallOfFameGameList(
    val year: Int,
    val games: List<HallOfFameGame>
)