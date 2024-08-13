package com.opencritic.auth.domain

class GetAuthStateInteractor(
    private val authStateRepository: AuthStateRepository,
) {
    suspend operator fun invoke(): Result<AuthState> =
        runCatching {
            authStateRepository.getState()
        }
}