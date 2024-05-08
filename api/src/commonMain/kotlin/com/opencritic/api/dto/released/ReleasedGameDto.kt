package com.opencritic.api.dto.released

import com.opencritic.api.dto.image.ImagesDto
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class ReleasedGameDto(
    val id: Long,
    val name: String,
    val firstReleaseDate: Instant,
    val tier: String,
    val images: ImagesDto,
    val topCriticScore: Float,
)
