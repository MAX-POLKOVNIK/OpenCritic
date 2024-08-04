package com.opencritic.game.your.ui.list

import com.opencritic.resources.images.IconResource
import com.opencritic.resources.images.Icons

data class GameListContent(
    val items: List<GameRowListItem>,
    val isShareVisible: Boolean,
    val shareIconResource: IconResource,
    val onShareClick: () -> Unit,
)

@Suppress("FunctionName")
fun GameListContent_PreviewData(): GameListContent =
    GameListContent(
        items = listOf(GameRowListItem_PreviewData()),
        isShareVisible = true,
        shareIconResource = Icons.share,
        onShareClick = {}
    )