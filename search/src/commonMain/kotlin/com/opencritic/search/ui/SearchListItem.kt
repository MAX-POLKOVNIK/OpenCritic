package com.opencritic.search.ui

import com.opencritic.resources.Color
import com.opencritic.resources.Colors
import com.opencritic.resources.StringProvider
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
            SearchItemKind.Game -> stringProvider.game
            SearchItemKind.Critic -> stringProvider.critic
            SearchItemKind.Outlet -> stringProvider.outlet
        },
        kindColor = when (item.kind) {
            SearchItemKind.Game -> Colors.Orange
            SearchItemKind.Critic -> Colors.Cyan
            SearchItemKind.Outlet -> Colors.Purple
        },
        onClick = onClick,
    )