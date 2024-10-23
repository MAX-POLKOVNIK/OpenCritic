package com.opencritic.games.details.domain

import kotlinx.datetime.Instant

data class GameRatingReview(
    val id: String,
    val user: GameRatingUser,
    val score: Float,
    // todo: should be scoreFormat object
    val scoreFormatId: Int,
    val summary: String,
    val date: Instant,
)