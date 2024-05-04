package com.opencritic.api.dto.game

import com.opencritic.api.dto.details.PlatformBriefDto
import com.opencritic.api.dto.image.ImagesDto
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BrowseGameDto(
    val id: Long,
    val name: String,
    val firstReleaseDate: Instant,
    val images: ImagesDto? = null,
    @SerialName("Platforms") val platforms: List<PlatformBriefDto>,
    val percentRecommended: Float,
    val tier: String = "",
    val numReviews: Int,
    val topCriticScore: Float,
)