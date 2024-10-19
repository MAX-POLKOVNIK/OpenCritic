package com.opencritic.halloffame.domain

import com.opencritic.games.GameRank

data class HallOfFameGame(
    val id: Long,
    val name: String,
    val rank: GameRank?,
    val posterImageUrl: String,
)