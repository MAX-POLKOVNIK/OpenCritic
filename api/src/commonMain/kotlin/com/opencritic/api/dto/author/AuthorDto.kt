package com.opencritic.api.dto.author

import com.opencritic.api.dto.image.ImageLinksDto
import kotlinx.serialization.Serializable

@Serializable
data class AuthorDto(
    val id: Int,
    val name: String,
    val imageSrc: ImageLinksDto? = null,
    val claimed: Boolean,
    val percentRecommended: Float,
    val numReviews: Int,
    val medianScore: Int,
    val averageScore: Float,

    val favoriteGames: List<String>? = null,
    val bio: String? = null,
    val hometown: String? = null,
    val xboxLive: String? = null,
    val psn: String? = null
)