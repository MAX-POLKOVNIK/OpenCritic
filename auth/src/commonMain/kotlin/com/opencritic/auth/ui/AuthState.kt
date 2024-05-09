package com.opencritic.auth.ui

import com.opencritic.auth.domain.AuthMethod
import com.opencritic.auth.domain.AuthUserAgent
import com.opencritic.mvvm.BaseErrorState
import com.opencritic.mvvm.BaseLoadingState
import com.opencritic.mvvm.ViewModelState
import com.opencritic.resources.ImageResourceProvider

sealed interface AuthState : ViewModelState {
    val titleText: String

    data class MethodList(
        override val titleText: String,
        val items: List<AuthMethodItem>
    ) : AuthState

    data class WebView(
        override val titleText: String,
        val url: String,
        val authUserAgent: AuthUserAgent,
        val redirectHandler: (String) -> Boolean
    ) : AuthState

    data class Loading(
        override val titleText: String,
    ) : BaseLoadingState(), AuthState

    data class Error(
        override val titleText: String,
        override val message: String,
        override val action: (() -> Unit)
    ) : BaseErrorState(message, action =  action), AuthState
}

@Suppress("FunctionName")
fun AuthStateMethodList_PreviewData(
    imageResourceProvider: ImageResourceProvider
): AuthState.MethodList =
    AuthState.MethodList(
        titleText = "Sign in",
        items = AuthMethod.entries
            .map { method ->
                AuthMethodItem(
                    imageResource = when (method) {
                        AuthMethod.Facebook -> imageResourceProvider.facebookLogoImage
                        AuthMethod.Google -> imageResourceProvider.googleLogoImage
                        AuthMethod.Twitch -> imageResourceProvider.twitchLogoImage
                        AuthMethod.Steam -> imageResourceProvider.steamLogoImage
                    },
                    text = method.name,
                    onClick = { }
                )
            }
    )
