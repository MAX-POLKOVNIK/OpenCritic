package com.opencritic.game.your.domain

import com.opencritic.games.GameRank

data class GameInList(
    val id: Long,
    val name: String,
    val posterUrl: String,
    val rank: GameRank?,
)