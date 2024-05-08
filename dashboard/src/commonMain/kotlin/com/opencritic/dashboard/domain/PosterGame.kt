package com.opencritic.dashboard.domain

import com.opencritic.games.GameRank

data class PosterGame(
    val id: Long,
    val name: String,
    val posterUrl: String,
    val rank: GameRank?,
)