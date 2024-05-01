package com.opencritic.dashboard.domain

data class Dashboard(
    val popularGames: List<PopularGame>,
    val deals: List<GameDeal>,
    val recentlyReleased: List<GameItem>,
    val upcoming: List<GameItem>,
    val reviewedToday: List<GameItem>,
)