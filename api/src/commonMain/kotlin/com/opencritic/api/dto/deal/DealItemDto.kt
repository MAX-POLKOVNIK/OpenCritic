package com.opencritic.api.dto.deal

import com.opencritic.api.dto.image.ImagesDto
import kotlinx.serialization.Serializable

@Serializable
data class DealItemDto(
    val id: Long,
    val name: String,
    val images: ImagesDto,
    val tier: String,
    val topCriticScore: Float,
    val featuredDeal: FeaturedDealDto,
)