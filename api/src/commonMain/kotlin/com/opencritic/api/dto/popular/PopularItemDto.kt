package com.opencritic.api.dto.popular

import com.opencritic.api.dto.image.ImagesDto
import kotlinx.serialization.Serializable

@Serializable
data class PopularItemDto(
    val images: ImagesDto,
    val topCriticScore: Float,
    val tier: String,
    val name: String,
    val id: Long,
)