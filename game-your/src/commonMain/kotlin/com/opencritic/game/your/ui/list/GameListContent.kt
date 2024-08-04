package com.opencritic.game.your.ui.list

data class GameListContent(
    val items: List<GameRowListItem>,
)

fun GameListContent_PreviewData(): GameListContent =
    GameListContent(
        items = listOf(GameRowListItem_PreviewData())
    )