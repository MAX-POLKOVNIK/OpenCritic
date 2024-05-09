package com.opencritic.auth.domain

import kotlinx.serialization.Serializable

@Serializable
data class AuthToken(
    val id: String,
    val token: String,
)