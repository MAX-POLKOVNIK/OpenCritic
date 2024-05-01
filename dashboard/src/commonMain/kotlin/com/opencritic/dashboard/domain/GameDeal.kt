package com.opencritic.dashboard.domain

data class GameDeal(
    val game: PopularGame,
    val name: String,
    val price: Float,
)