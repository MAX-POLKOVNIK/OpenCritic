package com.opencritic.games.details.ui

import com.opencritic.games.GameAction
import com.opencritic.resources.ImageResource
import com.opencritic.resources.ImageResourceProvider
import com.opencritic.resources.StringProvider

data class GameActionItem(
    val gameAction: GameAction,
    val imageResource: ImageResource,
    val text: String,
    val isSelected: Boolean,
    private val onClick: (GameActionItem) -> Unit,
)

fun GameActionItem(
    imageResourceProvider: ImageResourceProvider,
    stringProvider: StringProvider,
    action: GameAction,
    isSelected: Boolean,
    onClick: (GameActionItem) -> Unit,
): GameActionItem =
    GameActionItem(
        gameAction = action,
        imageResource = when (action) {
            GameAction.Want -> imageResourceProvider.gameActionWant
            GameAction.Played -> imageResourceProvider.gameActionPlayed
            GameAction.Favorite -> imageResourceProvider.gameActionFavorite
        },
        text = when (action) {
            GameAction.Want -> stringProvider.gameActionWant
            GameAction.Played -> stringProvider.gameActionPlayed
            GameAction.Favorite -> stringProvider.gameActionFavorite
        },
        isSelected = isSelected,
        onClick = onClick,
    )

fun gameActionItems(
    imageResourceProvider: ImageResourceProvider,
    stringProvider: StringProvider,
    isWanted: Boolean,
    isPlayed: Boolean,
    isFavorite: Boolean,
    onClick: (GameActionItem) -> Unit,
): List<GameActionItem> =
    GameAction.entries.map {
        GameActionItem(
            imageResourceProvider = imageResourceProvider,
            stringProvider = stringProvider,
            action = it,
            isSelected = when (it) {
                GameAction.Want -> isWanted
                GameAction.Played -> isPlayed
                GameAction.Favorite -> isFavorite
            },
            onClick = onClick
        )
    }
