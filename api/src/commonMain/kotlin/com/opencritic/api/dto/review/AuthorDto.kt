package com.opencritic.api.dto.review

import com.opencritic.api.dto.image.ImageLinksDto
import kotlinx.serialization.Serializable

@Serializable
data class AuthorDto(
    val id: Int,
    val name: String,
    val imageSrc: ImageLinksDto? = null,
)