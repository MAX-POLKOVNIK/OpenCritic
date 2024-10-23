package com.opencritic.api.dto.rating

import kotlinx.serialization.Serializable

@Serializable
data class GameRatingDto(
    val count: Int,
    val median: Int?,
)