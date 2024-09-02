package com.opencritic.dashboard.domain

data class FeaturedGameList(
    val name: String,
    val description: String,
    val games: List<PosterGame>,
)