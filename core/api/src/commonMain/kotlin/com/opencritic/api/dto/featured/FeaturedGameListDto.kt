package com.opencritic.api.dto.featured

import com.opencritic.api.dto.released.ReleasedGameDto
import kotlinx.serialization.Serializable

@Serializable
data class FeaturedGameListDto(
    val label: String,
    val description: String,
    val games: List<ReleasedGameDto>,
)
