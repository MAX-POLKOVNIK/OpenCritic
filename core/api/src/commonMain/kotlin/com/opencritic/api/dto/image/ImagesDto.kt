package com.opencritic.api.dto.image

import kotlinx.serialization.Serializable

@Serializable
data class ImagesDto(
    val box: ImageLinksDto? = null,
    val banner: ImageLinksDto? = null,
    val screenshots: List<ImageLinksDto>? = null,
    val square: ImageLinksDto? = null,
    val masthead: ImageLinksDto? = null,
)