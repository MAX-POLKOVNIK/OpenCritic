package com.opencritic.dashboard.domain

import com.opencritic.games.Tier

data class PopularGame(
    val id: Long,
    val name: String,
    val posterUrl: String,
    val score: Int,
    val tier: Tier,
)