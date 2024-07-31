package com.opencritic.search.ui

import com.opencritic.mvvm.ViewModelState
import com.opencritic.resources.Colors
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource

data class SearchState(
    val searchText: String,
    val searchHint: TextSource,
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
        searchHint = "Type something".asTextSource(),
        searchListItemsState = SearchItemsState.Content(
            items = listOf(
                SearchListItem(
                    id = 1,
                    nameText = "Game name",
                    kindText = "Game".asTextSource(),
                    kindColor = Colors.Orange,
                    onClick = {}
                ),
                SearchListItem(
                    id = 1,
                    nameText = "Some magazine",
                    kindText = "Outlet".asTextSource(),
                    kindColor = Colors.Cyan,
                    onClick = {}
                ),
                SearchListItem(
                    id = 1,
                    nameText = "Max Polkovnik",
                    kindText = "Critic".asTextSource(),
                    kindColor = Colors.Purple,
                    onClick = {}
                ),
            )
        ),
        onSearchChanged = { _, _  -> }
    )