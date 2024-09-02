package com.opencritic.auth.api.domain

interface GetAuthStateInteractor {
    suspend operator fun invoke(): Result<AuthState>
}