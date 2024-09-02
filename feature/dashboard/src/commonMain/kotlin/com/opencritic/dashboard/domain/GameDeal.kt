package com.opencritic.dashboard.domain

data class GameDeal(
    val game: PosterGame,
    val name: String,
    val price: Float?,
    val externalUrl: String,
)