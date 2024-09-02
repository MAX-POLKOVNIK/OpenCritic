package com.opencritic.api.dto.details

import kotlinx.serialization.Serializable

@Serializable
data class CompanyDto(
    val name: String,
    val type: String,
)