package com.opencritic.search.ui

import com.opencritic.mvvm.ViewModelState
import com.opencritic.resources.Colors

data class SearchState(
    val searchText: String,
    val searchHint: String,
    val searchListItemsState: SearchItemsState,
    private val onSearchChanged: (SearchState, String) -> Unit,
) : ViewModelState {
    fun changeSearch(criteria: String) =
        onSearchChanged(this, criteria)
}


@Suppress("FunctionName")
fun SearchState_PreviewData(): SearchState =
    SearchState(
        searchText = "Game to find",
        searchHint = "Type something",
        searchListItemsState = SearchItemsState.Content(
            items = listOf(
                SearchListItem(
                    id = 1,
                    nameText = "Game name",
                    kindText = "Game",
                    kindColor = Colors.Orange,
                    onClick = {}
                ),
                SearchListItem(
                    id = 1,
                    nameText = "Some magazine",
                    kindText = "Outlet",
                    kindColor = Colors.Cyan,
                    onClick = {}
                ),
                SearchListItem(
                    id = 1,
                    nameText = "Max Polkovnik",
                    kindText = "Critic",
                    kindColor = Colors.Purple,
                    onClick = {}
                ),
            )
        ),
        onSearchChanged = { _, _  -> }
    )