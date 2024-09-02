package com.opencritic.search.ui

import com.opencritic.resources.colors.Color
import com.opencritic.resources.colors.Colors
import com.opencritic.resources.MR
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource
import com.opencritic.search.domain.SearchItem
import com.opencritic.search.domain.SearchItemKind

data class SearchListItem(
    val id: Long,
    val nameText: String,
    val kindText: TextSource,
    val kindColor: Color,
    private val onClick: (SearchListItem) -> Unit,
) {
    fun click() = onClick(this)
}


fun SearchListItem(
    item: SearchItem,
    onClick: (SearchListItem) -> Unit,
): SearchListItem =
    SearchListItem(
        id = item.id,
        nameText = item.name,
        kindText = when (item.kind) {
            SearchItemKind.Game -> MR.strings.str_game.asTextSource()
            SearchItemKind.Critic -> MR.strings.str_critic.asTextSource()
            SearchItemKind.Outlet -> MR.strings.str_outlet.asTextSource()
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
        kindText = "Game".asTextSource(),
        kindColor = Colors.Cyan,
        onClick = {}
    )