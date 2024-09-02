package com.opencritic.api.dto.profile

import com.opencritic.api.dto.list.VitalListsDto
import kotlinx.serialization.Serializable

@Serializable
data class ProfileDto(
    val id: Int,
    val vitalLists: VitalListsDto,
)