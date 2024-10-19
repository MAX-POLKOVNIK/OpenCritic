package com.opencritic.api.dto.details

import kotlinx.serialization.Serializable

@Serializable
data class GameTrailerDto(
    val title: String,
    val videoId: String,
    val externalUrl: String,
)