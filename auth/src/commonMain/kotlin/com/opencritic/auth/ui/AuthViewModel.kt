package com.opencritic.auth.ui

import com.opencritic.auth.domain.AuthByTokenInteractor
import com.opencritic.auth.domain.GetAuthStateInteractor
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseContentViewModel
import com.opencritic.mvvm.CommonViewModelState
import com.opencritic.mvvm.LoadingState
import com.opencritic.resources.text.asTextSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class AuthViewModel(
    private val authByTokenInteractor: AuthByTokenInteractor,
    private val getAuthStateInteractor: GetAuthStateInteractor,
    private val logger: Logger,
) : BaseContentViewModel<AuthContent>() {
    override fun initialState(): CommonViewModelState<AuthContent> =
        CommonViewModelState.loading("Auth".asTextSource())

    override fun onStateInit() {
        super.onStateInit()

        scope.launch {
            val token = getAuthStateInteractor().getOrNull()?.authToken ?: ""

            setContent {
                AuthContent(
                    token = token,
                    tokenHint = "Token".asTextSource(),
                    descriptionText = "Get token from site. It is required to get your games from OpenCritics".asTextSource(),
                    authButtonText = "Auth".asTextSource(),
                    onAuthButtonClicked = { onAuth() },
                    onTokenChanged = { onTokenChanged(it) }
                )
            }
        }
    }

    private fun onAuth() {
        scope.launch {
            if (!isContentSet) {
                return@launch
            }

            setLoadingPopup(LoadingState)

            delay(5.seconds)

            authByTokenInteractor.invoke(token = requireContent().token)
                .onSuccess {
                    clearLoadingPopup()

                    requireRouter().navigateBack()
                }
                .onFailure { error ->
                    clearLoadingPopup()

                    setErrorPopup(
                        message = error.toString().asTextSource(),
                        actionText = "Ok".asTextSource(),
                        action = { clearErrorPopup() }
                    )

                    logger.log("Failed to login: $error")
                }
        }
    }

    private fun onTokenChanged(token: String) {
        mutableState.update {
            it.updateContent { copy(token = token) }
        }
    }
}