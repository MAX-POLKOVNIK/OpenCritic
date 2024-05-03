package com.opencritic.search.ui

import com.opencritic.mvvm.ViewModelState

data class SearchState(
    val searchText: String,
    val searchHint: String,
    val searchListItemsState: SearchItemsState,
    private val onSearchChanged: (SearchState, String) -> Unit,
) : ViewModelState {
    fun changeSearch(criteria: String) =
        onSearchChanged(this, criteria)
}