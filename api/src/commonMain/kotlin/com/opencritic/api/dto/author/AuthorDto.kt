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

    val favoriteGames: List<String> = emptyList(),
    val bio: String = "",
    val hometown: String = "",
    val xboxLive: String = "",
    val psn: String = ""
)