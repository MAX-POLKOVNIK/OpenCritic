package com.opencritic.api.dto.popular

import com.opencritic.api.dto.image.ImageBoxDto
import kotlinx.serialization.Serializable

@Serializable
data class PopularItemDto(
    val images: ImageBoxDto,
    val topCriticScore: String,
    val tier: String,
    val name: String,
    val id: Long,
)