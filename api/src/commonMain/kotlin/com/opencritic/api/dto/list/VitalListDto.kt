package com.opencritic.api.dto.list

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VitalListDto(
    @SerialName("_id") val id: String,
    val label: String,
    val isVital: Boolean,
    val isRanked: Boolean,
    val gamesEnhanced: List<VitalListGame>,
)