package com.opencritic.api.dto.details

import kotlinx.serialization.Serializable

@Serializable
data class PlatformDto(
    val id: Int,
    val name: String,
    val shortName: String,
)