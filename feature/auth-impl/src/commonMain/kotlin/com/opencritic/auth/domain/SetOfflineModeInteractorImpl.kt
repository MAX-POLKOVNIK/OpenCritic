package com.opencritic.auth.domain

import com.opencritic.auth.api.domain.SetOfflineModeInteractor

internal class SetOfflineModeInteractorImpl(
    private val authStateRepository: AuthStateRepository,
) : SetOfflineModeInteractor {
    override suspend operator fun invoke(isEnabled: Boolean): Result<Unit> =
        runCatching {
            authStateRepository.setOfflineMode(isEnabled)
        }
}