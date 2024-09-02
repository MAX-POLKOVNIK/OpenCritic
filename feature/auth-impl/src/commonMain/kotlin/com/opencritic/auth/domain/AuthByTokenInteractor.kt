package com.opencritic.auth.domain

import com.opencritic.auth.api.domain.SetOfflineModeInteractor

class AuthByTokenInteractor(
    private val authStateRepository: AuthStateRepository,
    private val setOfflineModeInteractor: SetOfflineModeInteractor,
) {
    suspend operator fun invoke(token: String): Result<Unit> =
        runCatching {
            authStateRepository.getProfileId(token)

            authStateRepository.setToken(token)

            setOfflineModeInteractor(isEnabled = false)
        }
}