package com.opencritic.dashboard.domain

data class Dashboard(
    val popularGames: List<PosterGame>,
    val deals: List<GameDeal>,
    val recentlyReleased: List<GameItem>,
    val upcoming: List<GameItem>,
    val reviewedToday: List<GameItem>,
    val hallOfFame: List<PosterGame>,
)