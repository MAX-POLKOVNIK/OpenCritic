package com.opencritic.api.dto.review

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class ReviewedTodayGameDto(
    val id: Long,
    val name: String,
    val tier: String,
    val topCriticScore: Float,
    val firstReleaseDate: Instant,
)
