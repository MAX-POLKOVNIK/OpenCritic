package com.opencritic.auth.ui

import com.opencritic.auth.domain.AuthByCallbackInteractor
import com.opencritic.auth.domain.AuthMethod
import com.opencritic.auth.domain.AuthRedirectInteractor
import com.opencritic.auth.domain.AuthUserAgent
import com.opencritic.logs.Logger
import com.opencritic.logs.log
import com.opencritic.mvvm.BaseViewModel
import com.opencritic.resources.MR
import com.opencritic.resources.images.SharedImages
import com.opencritic.resources.text.asTextSource
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRedirectInteractor: AuthRedirectInteractor,
    private val authByCallbackInteractor: AuthByCallbackInteractor,
    private val logger: Logger,
) : BaseViewModel<AuthState>() {
    override fun initialState(): AuthState =
        AuthState.MethodList(
            titleText = MR.strings.str_sign_in_title.asTextSource(),
            items = AuthMethod.entries
                .map { method ->
                    AuthMethodItem(
                        imageResource = when (method) {
                            AuthMethod.Facebook -> SharedImages.facebook
                            AuthMethod.Google -> SharedImages.google
                            AuthMethod.Twitch -> SharedImages.twitch
                            AuthMethod.Steam -> SharedImages.steam
                        },
                        text = method.name,
                        onClick = { onMethodSelected(method) }
                    )
                }
        )

    private fun onUrlRedirect(url: String): Boolean {
        val isAllowed = authRedirectInteractor(url)

        if (!isAllowed) {
            scope.launch {
                mutableState.tryEmit(
                    AuthState.Loading(MR.strings.str_sign_in_title.asTextSource())
                )

                authByCallbackInteractor(url)
                    .onSuccess {
                        requireRouter().navigateBack()
                    }
                    .onFailure {
                        mutableState.tryEmit(
                            AuthState.Error(
                                titleText = MR.strings.str_sign_in_title.asTextSource(),
                                message = it.toString(),
                                action = {
                                    mutableState.tryEmit(initialState())
                                }
                            )
                        )
                        logger.log(it)
                    }
            }
        }

        return isAllowed
    }

    private fun onMethodSelected(method: AuthMethod) =
        mutableState.tryEmit(
            AuthState.WebView(
                titleText = MR.strings.str_sign_in_title.asTextSource(),
                url = method.url,
                authUserAgent = AuthUserAgent,
                redirectHandler = ::onUrlRedirect
            )
        )
}