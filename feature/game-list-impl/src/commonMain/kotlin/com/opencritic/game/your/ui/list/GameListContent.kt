package com.opencritic.game.your.ui.list

import com.opencritic.mvvm.ActionedScreenContent
import com.opencritic.resources.images.IconResource
import com.opencritic.resources.images.Icons
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class GameListContent(
    val items: ImmutableList<GameRowListItem>,
    override val isActionVisible: Boolean,
    override val actionIconResource: IconResource,
    override val onAction: () -> Unit,
) : ActionedScreenContent

@Suppress("FunctionName")
fun GameListContent_PreviewData(): GameListContent =
    GameListContent(
        items = persistentListOf(GameRowListItem_PreviewData()),
        isActionVisible = true,
        actionIconResource = Icons.share,
        onAction = {}
    )