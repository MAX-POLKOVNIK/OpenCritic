package com.opencritic.api.dto.image

import kotlinx.serialization.Serializable

@Serializable
data class ImagesDto(
    val box: ImageBoxDto,
)