package com.opencritic.game.your.ui.list

import com.opencritic.mvvm.ActionedScreenContent
import com.opencritic.resources.images.IconResource
import com.opencritic.resources.images.Icons

data class GameListContent(
    val items: List<GameRowListItem>,
    override val isActionVisible: Boolean,
    override val actionIconResource: IconResource,
    override val onAction: () -> Unit,
) : ActionedScreenContent

@Suppress("FunctionName")
fun GameListContent_PreviewData(): GameListContent =
    GameListContent(
        items = listOf(GameRowListItem_PreviewData()),
        isActionVisible = true,
        actionIconResource = Icons.share,
        onAction = {}
    )