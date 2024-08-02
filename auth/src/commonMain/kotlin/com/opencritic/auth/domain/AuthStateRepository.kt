package com.opencritic.auth.domain

import com.opencritic.api.dto.profile.ProfileDto

interface AuthStateRepository {
    suspend fun getState(): AuthState
    suspend fun setToken(token: String?)
    suspend fun setOfflineMode(isEnabled: Boolean)
    suspend fun getProfileId(token: String): Int
}