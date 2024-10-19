package com.opencritic.api.dto.image

import kotlinx.serialization.Serializable

@Serializable
data class ImageLinksDto(
    val og: String? = null,
    val sm: String? = null,
    val lg: String? = null,
)