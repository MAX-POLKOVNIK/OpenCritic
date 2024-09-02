package com.opencritic.auth.domain

import com.opencritic.auth.api.domain.AuthState
import com.opencritic.auth.api.domain.GetAuthStateInteractor

internal class GetAuthStateInteractorImpl(
    private val authStateRepository: AuthStateRepository,
) : GetAuthStateInteractor {
    override suspend operator fun invoke(): Result<AuthState> =
        runCatching {
            authStateRepository.getState()
        }
}