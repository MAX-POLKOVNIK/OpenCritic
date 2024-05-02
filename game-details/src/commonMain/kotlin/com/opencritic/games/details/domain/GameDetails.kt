package com.opencritic.games.details.domain

import com.opencritic.games.Company
import com.opencritic.games.GameRank
import com.opencritic.games.Platform
import com.opencritic.games.Review
import com.opencritic.games.Trailer
import kotlinx.datetime.Instant

data class GameDetails(
    val posterUrl: String,
    val isWanted: Boolean,
    val isPlayed: Boolean,
    val isFavorite: Boolean,
    val name: String,
    val companies: List<Company>,
    val releaseDate: Instant,
    val platforms: List<Platform>,
    val rank: GameRank?,
    val recommendPercent: Int?,
    val reviews: List<Review>,
    val reviewCount: Int,
    val trailers: List<Trailer>,
    val screenshotUrls: List<String>,
)