package com.opencritic.search.ui

sealed interface SearchItemsState {
    data class Error(val message: String) : SearchItemsState
    data object Loading : SearchItemsState
    data class Empty(val message: String) : SearchItemsState
    data class Content(
        val items: List<SearchListItem>
    ) : SearchItemsState
}