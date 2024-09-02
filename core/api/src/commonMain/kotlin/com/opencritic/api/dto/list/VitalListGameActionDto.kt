package com.opencritic.api.dto.list

import kotlinx.serialization.Serializable

@Serializable
data class VitalListGameActionDto(
    val action: String,
    val gameId: Long,
) {
    constructor(
        action: ListGameActionDto,
        gameId: Long
    ) : this(action.value, gameId)
}