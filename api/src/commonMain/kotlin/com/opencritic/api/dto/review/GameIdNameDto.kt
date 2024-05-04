package com.opencritic.api.dto.review

import kotlinx.serialization.Serializable

@Serializable
data class GameIdNameDto(
    val id: Long,
    val name: String,
)