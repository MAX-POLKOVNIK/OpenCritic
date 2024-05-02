package com.opencritic.api.dto.review

import kotlinx.serialization.Serializable

@Serializable
data class AuthorDto(
    val id: Int,
    val name: String,
    val image: Boolean,
)