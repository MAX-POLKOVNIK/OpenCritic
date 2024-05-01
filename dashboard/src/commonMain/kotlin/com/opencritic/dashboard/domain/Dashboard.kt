package com.opencritic.dashboard.domain

data class Dashboard(
    val popularGames: List<PopularGame>,
    val deals: List<GameDeal>,
)