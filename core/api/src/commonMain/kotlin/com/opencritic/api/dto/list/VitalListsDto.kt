package com.opencritic.api.dto.list

import kotlinx.serialization.Serializable

@Serializable
data class VitalListsDto(
    val favoriteList: VitalListDto,
    val playedList: VitalListDto,
    val wantList: VitalListDto,
)