package com.opencritic.api.dto.platform

import kotlinx.serialization.Serializable

@Serializable
data class PlatformDto(
    val id: Int,
    val order: Int,
    val name: String,
    val shortName: String,
    val imageSrcV2: String? = null,
)