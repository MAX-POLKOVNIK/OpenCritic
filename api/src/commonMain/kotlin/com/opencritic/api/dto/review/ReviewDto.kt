package com.opencritic.api.dto.review

import com.opencritic.api.dto.details.PlatformDto
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewDto(
    @SerialName("_id") val id: String,
    @SerialName("Outlet") val outlet: OutletDto,
    @SerialName("ScoreFormat") val scoreFormat: ScoreFormatDto,
    val externalUrl: String,
    @SerialName("Platforms") val platforms: List<PlatformDto>,
    @SerialName("Authors") val authors: List<AuthorDto>,
    val alias: String? = null,
    val publishedDate: Instant,
    val title: String? = null,
    val score: Float? = null,
    val snippet: String? = null,
    val game: GameIdNameDto,
)