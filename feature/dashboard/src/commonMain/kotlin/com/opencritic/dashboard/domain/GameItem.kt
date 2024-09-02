package com.opencritic.dashboard.domain

import com.opencritic.games.GameRank
import kotlinx.datetime.Instant

data class GameItem(
    val id: Long,
    val name: String,
    val releaseDate: Instant,
    val rank: GameRank?
)