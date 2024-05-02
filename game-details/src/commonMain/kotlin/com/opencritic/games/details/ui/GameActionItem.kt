package com.opencritic.games.details.ui

import com.opencritic.games.GameAction
import com.opencritic.resources.ImageResource
import com.opencritic.resources.ImageResourceProvider
import com.opencritic.resources.StringResourceProvider
import com.opencritic.resources.get

data class GameActionItem(
    val gameAction: GameAction,
    val imageResource: ImageResource,
    val text: String,
    val isSelected: Boolean,
    private val onClick: (GameActionItem) -> Unit,
)

fun GameActionItem(
    imageResourceProvider: ImageResourceProvider,
    stringResourceProvider: StringResourceProvider,
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
            GameAction.Want -> stringResourceProvider.gameActionWant.get(stringResourceProvider)
            GameAction.Played -> stringResourceProvider.gameActionPlayed.get(stringResourceProvider)
            GameAction.Favorite -> stringResourceProvider.gameActionFavorite.get(stringResourceProvider)
        },
        isSelected = isSelected,
        onClick = onClick,
    )

fun gameActionItems(
    imageResourceProvider: ImageResourceProvider,
    stringResourceProvider: StringResourceProvider,
    isWanted: Boolean,
    isPlayed: Boolean,
    isFavorite: Boolean,
    onClick: (GameActionItem) -> Unit,
): List<GameActionItem> =
    GameAction.entries.map {
        GameActionItem(
            imageResourceProvider = imageResourceProvider,
            stringResourceProvider = stringResourceProvider,
            action = it,
            isSelected = when (it) {
                GameAction.Want -> isWanted
                GameAction.Played -> isPlayed
                GameAction.Favorite -> isFavorite
            },
            onClick = onClick
        )
    }
