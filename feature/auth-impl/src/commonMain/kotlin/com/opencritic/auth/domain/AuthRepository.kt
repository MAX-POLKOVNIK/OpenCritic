package com.opencritic.auth.domain

interface AuthRepository {
    val isAuthorized: Boolean
        get() = authToken != null

    var authToken: AuthToken?

    suspend fun authorizeByCallback(callbackUrl: String)
}