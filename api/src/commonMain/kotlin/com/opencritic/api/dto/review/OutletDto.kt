package com.opencritic.api.dto.review

import com.opencritic.api.dto.image.ImageLinksDto
import kotlinx.serialization.Serializable

@Serializable
data class OutletDto(
    val id: Int,
    val name: String,
    val isContributor: Boolean = false,
    val imageSrc: ImageLinksDto = ImageLinksDto(),
)