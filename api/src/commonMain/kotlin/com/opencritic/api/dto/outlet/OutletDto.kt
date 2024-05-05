package com.opencritic.api.dto.outlet

import com.opencritic.api.dto.image.ImageLinksDto
import kotlinx.serialization.Serializable

@Serializable
data class OutletDto(
    val id: Int,
    val imageSrc: ImageLinksDto,
    val percentRecommended: Float,
    val numReviews: Int,
    val medianScore: Float,
    val averageScore: Float,
    val name: String,
    val externalUrl: String,
)