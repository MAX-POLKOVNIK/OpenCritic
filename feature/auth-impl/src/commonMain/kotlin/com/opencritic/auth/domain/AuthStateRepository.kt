package com.opencritic.auth.domain

import com.opencritic.auth.api.domain.AuthState

interface AuthStateRepository {
    suspend fun getState(): AuthState
    suspend fun setToken(token: String?)
    suspend fun setOfflineMode(isEnabled: Boolean)
    suspend fun getProfileId(token: String): Int
}