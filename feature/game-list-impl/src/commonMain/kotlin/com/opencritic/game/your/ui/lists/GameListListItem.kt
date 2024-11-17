package com.opencritic.game.your.ui.lists

import com.opencritic.game.your.domain.GameList
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

data class GameListListItem(
    internal val id: String,
    internal val name: String,
    internal val shareLink: String,
    val posterUrls: ImmutableList<String>,
    val nameText: TextSource,
    val gamesText: TextSource,
    val isShareButtonVisible: Boolean,
    val shareButtonText: TextSource,
    val editButtonText: TextSource,
    private val onShareClick: (GameListListItem) -> Unit,
    private val onEditClick: (GameListListItem) -> Unit = ::emptyClickHandler,
    private val onClick: (GameListListItem) -> Unit,
) {
    fun shareClick() = onShareClick(this)
    fun editClick() = onEditClick(this)
    fun click() = onClick(this)

    companion object {
        fun emptyClickHandler(item: GameListListItem) {}
    }
}

fun GameListListItem(
    gameList: GameList,
    onClick: (GameListListItem) -> Unit,
    onShareClick: (GameListListItem) -> Unit,
    onEditClick: (GameListListItem) -> Unit = GameListListItem.Companion::emptyClickHandler,
): GameListListItem =
    GameListListItem(
        id = gameList.id,
        name = gameList.name,
        shareLink = gameList.shareLink,
        posterUrls = gameList.posters.take(4).toImmutableList(),
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
        id = "",
        name = "",
        shareLink = "",
        posterUrls = persistentListOf(
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

