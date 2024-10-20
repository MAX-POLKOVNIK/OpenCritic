package com.opencritic.game.your.ui.lists

import com.opencritic.game.your.domain.GameList
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource

data class GameListListItem(
    val posterUrls: List<String>,
    val nameText: TextSource,
    val gamesText: TextSource,
    val isShareButtonVisible: Boolean,
    val shareButtonText: TextSource,
    val editButtonText: TextSource,
    val onShareClick: () -> Unit,
    val onEditClick: () -> Unit,
    val onClick: () -> Unit,
)

fun GameListListItem(
    gameList: GameList,
    onShareClick: () -> Unit,
    onEditClick: () -> Unit,
    onClick: () -> Unit,
): GameListListItem =
    GameListListItem(
        posterUrls = gameList.posters.take(4),
        nameText = gameList.name.asTextSource(),
        gamesText = "${gameList.gamesCount} games on list".asTextSource(),
        isShareButtonVisible = gameList.shareLink.isNotBlank(),
        shareButtonText = "Share".asTextSource(),
        editButtonText = "Edit".asTextSource(),
        onShareClick = onShareClick,
        onEditClick = onEditClick,
        onClick = onClick,
    )

@Suppress("FunctionName")
fun GameListListItem_PreviewData(): GameListListItem =
    GameListListItem(
        posterUrls = listOf(
            "https://img.opencritic.com/game/14353/cFkNFNOs.jpg",
            "https://img.opencritic.com/game/14353/cFkNFNOs.jpg",
            "https://img.opencritic.com/game/14353/cFkNFNOs.jpg",
            "https://img.opencritic.com/game/14353/cFkNFNOs.jpg",
        ),
        isShareButtonVisible = true,
        nameText = "Your Want-To-Play Games".asTextSource(),
        gamesText = "44 games on list".asTextSource(),
        shareButtonText = "Share".asTextSource(),
        editButtonText = "Edit".asTextSource(),
        onShareClick = {},
        onEditClick = {},
        onClick = {},
    )

