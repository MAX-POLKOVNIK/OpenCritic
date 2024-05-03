package com.opencritic.games.details.domain

data class Outlet(
    val id: Int,
    val imageUrl: String,
    val percentRecommended: Float,
    val reviewsCount: Int,
    val medianScore: Int,
    val averageScore: Float,
    val name: String,
    val externalUrl: String,
)