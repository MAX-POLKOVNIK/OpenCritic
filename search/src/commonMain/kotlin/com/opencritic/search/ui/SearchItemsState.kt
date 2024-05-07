package com.opencritic.search.ui

import com.opencritic.mvvm.BaseErrorState
import com.opencritic.mvvm.BaseLoadingState

sealed interface SearchItemsState {
    data class Error(override val message: String) : BaseErrorState(message), SearchItemsState
    data object Loading : BaseLoadingState(), SearchItemsState
    data class Empty(val message: String) : SearchItemsState
    data class Content(
        val items: List<SearchListItem>
    ) : SearchItemsState
}