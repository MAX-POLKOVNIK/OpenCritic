package com.opencritic.api.dto.search

import kotlinx.serialization.Serializable

@Serializable
data class SearchItemDto(
    val id: Long,
    val name: String,
    val relation: String,
)