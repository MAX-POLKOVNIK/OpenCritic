package com.opencritic.auth.domain

class SetOfflineModeInteractor(
    private val authStateRepository: AuthStateRepository,
) {
    suspend operator fun invoke(isEnabled: Boolean): Result<Unit> =
        runCatching {
            authStateRepository.setOfflineMode(isEnabled)
        }
}