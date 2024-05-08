package com.opencritic.api.dto.deal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeaturedDealDto(
    @SerialName("_id")
    val id: String,
    val externalUrl: String,
    val name: String,
    val basePrice: Float,
    val price: Float,
)
