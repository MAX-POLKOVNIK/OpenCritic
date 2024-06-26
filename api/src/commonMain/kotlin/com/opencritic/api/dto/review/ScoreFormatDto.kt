package com.opencritic.api.dto.review

import kotlinx.serialization.Serializable

@Serializable
data class ScoreFormatDto(
    val id: Int,
    val name: String,
    val shortName: String,
    val scoreDisplay: String?,
    val isNumeric: Boolean,
    val isSelect: Boolean,
    val isStars: Boolean? = null,
    val numDecimals: Int? = null,
    val base: Int?,
    val options: List<ScoreFormatOptionDto>?,
)