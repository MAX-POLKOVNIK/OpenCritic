package com.opencritic.auth.api.domain

data class AuthState(
    val authToken: String?,
    val isOfflineMode: Boolean,
) {
    val isLoggedIn: Boolean =
        !authToken.isNullOrBlank()

    val shouldAskToLogin: Boolean =
        !isLoggedIn == !isOfflineMode
}