package com.opencritic.api.dto.rating

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class GameRatingReviewDto(
    val id: String,
    val userId: Int,
    val score: Float,
    val scoreFormat: Int,
    val recommend: Boolean,
    val createdAt: Instant,
    val updatedAt: Instant,
    val user: GameRatingUserDto,
    val count: Int,
    val status: String,
    val excerpt: String,
)