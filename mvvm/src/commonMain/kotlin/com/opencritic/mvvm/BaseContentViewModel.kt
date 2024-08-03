package com.opencritic.mvvm

import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

abstract class BaseContentViewModel<Content> : BaseViewModel<CommonViewModelState<Content>>() {
    private var toastJob: Job? = null

    protected val isContentSet: Boolean
        get() = state.value.content != null

    protected fun requireContent(): Content =
        requireNotNull(state.value.content) { "Content is not set" }

    protected fun setContent(block: () -> Content) {
        mutableState.update { state ->
            state.content(
                title = state.title,
                content = block()
            )
        }
    }

    protected fun updateContentIfSet(block: Content.() -> Content) {
        mutableState.update { state ->
            if (state.content == null) state
            else state.updateContent { block(this) }
        }
    }

    protected fun loading(baseLoadingState: BaseLoadingState = LoadingState) {
        mutableState.update {
            it.loading(baseLoadingState)
        }
    }

    protected fun hideLoading() {
        mutableState.update {
            it.clearLoading()
        }
    }

    protected fun error(baseErrorState: BaseErrorState) {
        mutableState.update {
            it.error(baseErrorState)
        }
    }

    protected fun setLoadingPopup(loadingPopup: BaseLoadingState) {
        mutableState.update { state ->
            state.setLoadingPopup(loadingPopup)
        }
    }

    protected fun clearLoadingPopup() {
        mutableState.update { it.clearLoadingPopup() }
    }

    fun setErrorPopup(
        message: TextSource,
        actionText: TextSource? = null,
        action: (() -> Unit)? = null
    ) {
        mutableState.update { state ->
            state.setErrorPopup(message, actionText, action)
        }
    }

    protected fun setErrorPopup(errorPopup: BaseErrorState) {
        mutableState.update { state ->
            state.setErrorPopup(errorPopup)
        }
    }

    protected fun clearErrorPopup() {
        mutableState.update { it.clearErrorPopup() }
    }

    protected fun showToast(
        text: String,
        duration: Duration = 3.seconds
    ) = showToast(
        text = text.asTextSource(),
        duration = duration,
    )

    protected fun showToast(
        text: TextSource,
        duration: Duration = 3.seconds
    ) {
        toastJob?.cancel()

        toastJob = scope.launch {
            mutableState.update {
                it.setToast(text)
            }

            delay(duration)

            mutableState.update { it.clearToast() }
        }
    }
}
