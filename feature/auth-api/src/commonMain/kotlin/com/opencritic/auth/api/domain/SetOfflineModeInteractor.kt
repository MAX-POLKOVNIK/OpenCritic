package com.opencritic.auth.api.domain

interface SetOfflineModeInteractor {
    suspend operator fun invoke(isEnabled: Boolean): Result<Unit>
}