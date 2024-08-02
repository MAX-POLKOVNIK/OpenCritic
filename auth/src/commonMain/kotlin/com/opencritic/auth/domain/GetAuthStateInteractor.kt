package com.opencritic.auth.domain

import com.opencritic.database.UserPreferencesDao

class GetAuthStateInteractor(
    private val authStateRepository: AuthStateRepository,
) {
    suspend operator fun invoke(): Result<AuthState> =
        runCatching {
            authStateRepository.getState()
        }
}