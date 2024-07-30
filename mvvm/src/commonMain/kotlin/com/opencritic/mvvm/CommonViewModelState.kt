package com.opencritic.mvvm

data class CommonViewModelState<Content>(
    val content: Content?,
    val fullScreenLoading: BaseLoadingState?,
    val fullScreenError: BaseErrorState?,
//    val loadingPopup: LoadingPopupModel?,
//    val errorPopup: ErrorPopupModel?,
//    val toast: Toast?,
) : ViewModelState {
    val isFullScreenLoading: Boolean =
        fullScreenLoading != null

    val isFullScreenError: Boolean =
        fullScreenError != null

    val isContent: Boolean =
        !isFullScreenLoading && !isFullScreenError && content != null

    fun content(
        content: Content,
        shouldNullifyLoading: Boolean = true,
        shouldNullifyError: Boolean = true,
    ): CommonViewModelState<Content> =
        when (shouldNullifyLoading to shouldNullifyError) {
            false to false -> copy(content = content)
            true to false -> copy(content = content, fullScreenLoading = null)
            false to true -> copy(content = content, fullScreenError = null)
            else -> copy(
                content = content,
                fullScreenLoading = null,
                fullScreenError = null,
            )
        }

    fun error(errorDescription: String): CommonViewModelState<Content> =
        error(fullScreenError = ErrorState(errorDescription))

    fun error(fullScreenError: BaseErrorState): CommonViewModelState<Content> =
        copy(fullScreenError = fullScreenError)

    fun loading(
        fullScreenLoading: BaseLoadingState,
        shouldNullifyError: Boolean = true
    ): CommonViewModelState<Content> =
        if (shouldNullifyError) {
            copy(fullScreenLoading = fullScreenLoading, fullScreenError = null)
        } else {
            copy(fullScreenLoading = fullScreenLoading)
        }

    companion object {
        fun <Content> content(content: Content): CommonViewModelState<Content> =
            CommonViewModelState(
                content = content,
                fullScreenLoading = null,
                fullScreenError = null,
            )

        fun <Content> loading(loading: BaseLoadingState? = null): CommonViewModelState<Content> =
            CommonViewModelState<Content>(
                content = null,
                fullScreenLoading = loading ?: LoadingState,
                fullScreenError = null,
            )
    }
}