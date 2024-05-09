package com.opencritic.auth.domain

class AuthByCallbackInteractor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(callbackUrl: String): Result<Unit> =
        runCatching { authRepository.authorizeByCallback(callbackUrl) }
}