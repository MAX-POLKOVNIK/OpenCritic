package com.opencritic.api.dto.list

import com.opencritic.api.dto.details.GameDetailsDto
import com.opencritic.api.dto.popular.PopularItemDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameListDto(
    @SerialName("_id") val id: String,
    val label: String,
    val isVital: Boolean,
    val vitalIdentifier: String? = null,
    val numGames: Int,
    val gamesPopulated: List<PopularItemDto>,
)