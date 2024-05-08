package com.opencritic.api.dto.details

import com.opencritic.api.dto.image.ImagesDto
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDetailsDto(
    val id: Long,
    val images: ImagesDto,
    val percentRecommended: Float,
    val numReviews: Int,
    val topCriticScore: Float,
    val tier: String,
    val name: String,
    val trailers: List<GameTrailerDto>,
    @SerialName("Companies") val companies: List<CompanyDto>,
    @SerialName("Platforms") val platforms: List<PlatformBriefDto>,
    val firstReleaseDate: Instant,
)