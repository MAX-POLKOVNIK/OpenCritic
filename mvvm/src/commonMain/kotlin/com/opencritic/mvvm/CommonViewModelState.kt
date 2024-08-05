package com.opencritic.mvvm

import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource

data class CommonViewModelState<Content>(
    val title: TextSource?,
    val content: Content?,
    val fullScreenLoading: BaseLoadingState?,
    val fullScreenError: BaseErrorState?,
    val loadingPopup: BaseLoadingState?,
    val errorPopup: BaseErrorState?,
    val toast: Toast?,
) : ViewModelState {
    val isFullScreenLoading: Boolean =
        fullScreenLoading != null

    val isFullScreenError: Boolean =
        fullScreenError != null

    val isContent: Boolean =
        !isFullScreenLoading && !isFullScreenError && content != null

    fun updateContent(block: Content.() -> Content): CommonViewModelState<Content> =
        if (content == null) this
        else copy(content = block(content))

    fun content(
        content: Content,
        title: TextSource? = null,
        shouldNullifyLoading: Boolean = true,
        shouldNullifyError: Boolean = true,
    ): CommonViewModelState<Content> =
        when (shouldNullifyLoading to shouldNullifyError) {
            false to false -> copy(content = content)
            true to false -> copy(content = content, fullScreenLoading = null)
            false to true -> copy(content = content, fullScreenError = null)
            else -> copy(
                title = title ?: title,
                content = content,
                fullScreenLoading = null,
                fullScreenError = null,
            )
        }

    fun error(
        errorDescription: String,
        title: TextSource? = null
    ): CommonViewModelState<Content> =
        error(
            title = title ?: this.title,
            fullScreenError = ErrorState(errorDescription.asTextSource())
        )

    fun error(
        fullScreenError: BaseErrorState,
        title: TextSource? = null,
        shouldNullifyLoading: Boolean = true,
    ): CommonViewModelState<Content> =
        copy(
            title = title ?: this.title,
            fullScreenError = fullScreenError,
            fullScreenLoading = fullScreenLoading.takeUnless { shouldNullifyLoading }
        )

    fun loading(
        fullScreenLoading: BaseLoadingState,
        shouldNullifyError: Boolean = true
    ): CommonViewModelState<Content> =
        if (shouldNullifyError) {
            copy(
                title = title,
                fullScreenLoading = fullScreenLoading,
                fullScreenError = null
            )
        } else {
            copy(
                title = title,
                fullScreenLoading = fullScreenLoading
            )
        }

    fun clearLoading(): CommonViewModelState<Content> =
        copy(fullScreenLoading = null)

    fun setToast(text: TextSource): CommonViewModelState<Content> =
        copy(toast = Toast(text))

    fun clearToast(): CommonViewModelState<Content> =
        copy(toast = null)

    fun setLoadingPopup(loadingPopup: BaseLoadingState): CommonViewModelState<Content> =
        copy(loadingPopup = loadingPopup)

    fun clearLoadingPopup(): CommonViewModelState<Content> =
        copy(loadingPopup = null)

    fun setErrorPopup(
        message: TextSource,
        actionText: TextSource,
        action: () -> Unit
    ): CommonViewModelState<Content> =
        setErrorPopup(
            errorPopup = ErrorState(
                message = message,
                actionText = actionText,
                action = action
            )
        )

    fun setErrorPopup(errorPopup: BaseErrorState?): CommonViewModelState<Content> =
        copy(errorPopup = errorPopup)

    fun clearErrorPopup(): CommonViewModelState<Content> =
        copy(errorPopup = null)

    companion object {
        fun <Content> content(
            content: Content,
            title: TextSource? = null
        ): CommonViewModelState<Content> =
            CommonViewModelState(
                title = title,
                content = content,
                fullScreenLoading = null,
                fullScreenError = null,
                toast = null,
                loadingPopup = null,
                errorPopup = null,
            )

        fun <Content> loading(
            title: TextSource? = null,
            loading: BaseLoadingState? = null
        ): CommonViewModelState<Content> =
            CommonViewModelState<Content>(
                title = title,
                content = null,
                fullScreenLoading = loading ?: LoadingState,
                fullScreenError = null,
                toast = null,
                loadingPopup = null,
                errorPopup = null,
            )
    }
}