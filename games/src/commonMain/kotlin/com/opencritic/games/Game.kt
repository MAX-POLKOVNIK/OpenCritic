package com.opencritic.games

import kotlinx.datetime.Instant

data class Game(
    val id: Long,
    val name: String,
    val releaseDate: Instant,
    val rank: GameRank?,
    val recommendPercent: Int?,
    val squareImageUrl: String,
    val bannerImageUrl: String,
    val companies: List<Company>,
    val platforms: List<Platform>,
    val reviewsCount: Int,
    val trailers: List<Trailer>,
    val screenshotUrls: List<String>,
)