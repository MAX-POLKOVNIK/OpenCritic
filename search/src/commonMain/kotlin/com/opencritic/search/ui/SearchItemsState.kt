package com.opencritic.search.ui

import com.opencritic.mvvm.BaseErrorState
import com.opencritic.mvvm.BaseLoadingState
import com.opencritic.resources.text.TextSource

sealed interface SearchItemsState {
    data class Error(override val message: String) : BaseErrorState(message), SearchItemsState
    data object Loading : BaseLoadingState(), SearchItemsState
    data class Empty(val message: TextSource) : SearchItemsState
    data class Content(
        val items: List<SearchListItem>
    ) : SearchItemsState
}