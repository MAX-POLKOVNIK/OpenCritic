package com.opencritic.search.ui

import com.opencritic.resources.Color
import com.opencritic.resources.Colors
import com.opencritic.resources.MR
import com.opencritic.resources.StringProvider
import com.opencritic.resources.getString
import com.opencritic.search.domain.SearchItem
import com.opencritic.search.domain.SearchItemKind

data class SearchListItem(
    val id: Long,
    val nameText: String,
    val kindText: String,
    val kindColor: Color,
    private val onClick: (SearchListItem) -> Unit,
) {
    fun click() = onClick(this)
}


fun SearchListItem(
    item: SearchItem,
    stringProvider: StringProvider,
    onClick: (SearchListItem) -> Unit,
): SearchListItem =
    SearchListItem(
        id = item.id,
        nameText = item.name,
        kindText = when (item.kind) {
            SearchItemKind.Game -> stringProvider.getString(MR.strings.str_game)
            SearchItemKind.Critic -> stringProvider.getString(MR.strings.str_critic)
            SearchItemKind.Outlet -> stringProvider.getString(MR.strings.str_outlet)
        },
        kindColor = when (item.kind) {
            SearchItemKind.Game -> Colors.Orange
            SearchItemKind.Critic -> Colors.Cyan
            SearchItemKind.Outlet -> Colors.Purple
        },
        onClick = onClick,
    )

@Suppress("FunctionName")
fun SearchListItem_PreviewData(): SearchListItem =
    SearchListItem(
        id = 1,
        nameText = "Game name",
        kindText = "Game",
        kindColor = Colors.Cyan,
        onClick = {}
    )