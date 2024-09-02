package com.opencritic.game.browser.domain

import com.opencritic.games.GameRank
import kotlinx.datetime.Instant

data class BrowseGame(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val rank: GameRank?,
    val percentRecommended: Float,
    val releaseDate: Instant,
)