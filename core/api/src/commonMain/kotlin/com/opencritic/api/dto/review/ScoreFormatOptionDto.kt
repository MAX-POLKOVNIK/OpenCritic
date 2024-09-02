package com.opencritic.api.dto.review

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScoreFormatOptionDto(
    @SerialName("val") val value: Int,
    val label: String,
)