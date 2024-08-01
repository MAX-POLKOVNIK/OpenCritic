package com.opencritic.mvvm

import com.opencritic.resources.text.TextSource

data class CommonViewModelState<Content>(
    val title: TextSource?,
    val content: Content?,
    val fullScreenLoading: BaseLoadingState?,
    val fullScreenError: BaseErrorState?,
//    val loadingPopup: LoadingPopupModel?,
//    val errorPopup: ErrorPopupModel?,
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
                title = title,
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
            title = title,
            fullScreenError = ErrorState(errorDescription)
        )

    fun error(
        fullScreenError: BaseErrorState,
        title: TextSource? = null
    ): CommonViewModelState<Content> =
        copy(
            title = title,
            fullScreenError = fullScreenError
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

    fun setToast(text: TextSource): CommonViewModelState<Content> =
        copy(toast = Toast(text))

    fun clearToast(): CommonViewModelState<Content> =
        copy(toast = null)

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
            )
    }
}