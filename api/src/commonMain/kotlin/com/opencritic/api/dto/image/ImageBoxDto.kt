package com.opencritic.api.dto.image

import kotlinx.serialization.Serializable

@Serializable
data class ImageBoxDto(
    val og: String,
    val sm: String,
)