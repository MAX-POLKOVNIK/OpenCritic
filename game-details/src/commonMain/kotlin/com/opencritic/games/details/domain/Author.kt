package com.opencritic.games.details.domain

data class Author(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val isClaimed: Boolean,
    val percentRecommended: Float,
    val reviewCount: Int,
    val medianScore: Int,
    val averageScore: Float,

    val favoriteGames: List<String>,
    val bio: String,
    val hometown: String,
    val xboxLive: String,
    val psn: String,
)