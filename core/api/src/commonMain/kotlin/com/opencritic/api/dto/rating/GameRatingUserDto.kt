package com.opencritic.api.dto.rating

import kotlinx.serialization.Serializable

@Serializable
data class GameRatingUserDto(
    val id: Int,
    val displayName: String,
    val slug: String,
)