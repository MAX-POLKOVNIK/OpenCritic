package com.opencritic.game.your.ui

import com.opencritic.game.your.domain.YourGame
import com.opencritic.game.your.domain.YourGameAction
import com.opencritic.resources.Color
import com.opencritic.resources.Colors
import com.opencritic.resources.images.IconResource
import com.opencritic.resources.images.Icons

data class YourGameIndicatorSmallItem(
    val wantedImageResource: IconResource,
    val wantedBackgroundColor: Color?,
    val wantedIconColor: Color?,
    val playedImageResource: IconResource,
    val playedBackgroundColor: Color?,
    val playedIconColor: Color?,
    val favoriteImageResource: IconResource,
    val favoriteBackgroundColor: Color?,
    val favoriteIconColor: Color?,
    private val onClick: (YourGameAction) -> Unit,
) {
    fun wantedClick() = onClick(YourGameAction.Want)
    fun playedClick() = onClick(YourGameAction.Played)
    fun favoriteClick() = onClick(YourGameAction.Favorite)
}

fun YourGameIndicatorSmallItem(
    game: YourGame,
    onClick: (YourGameAction) -> Unit,
): YourGameIndicatorSmallItem =
    YourGameIndicatorSmallItem(
        wantedImageResource = Icons.gameActionWant,
        wantedBackgroundColor = Colors.WantedGameColor.takeIf { game.isWanted },
        wantedIconColor = Colors.White.takeIf { game.isWanted },
        playedImageResource = Icons.gameActionPlayed,
        playedBackgroundColor = Colors.PlayedGameColor.takeIf { game.isPlayed },
        playedIconColor = Colors.White.takeIf { game.isPlayed },
        favoriteImageResource = Icons.gameActionFavorite,
        favoriteBackgroundColor = Colors.FavoriteGameColor.takeIf { game.isFavorite },
        favoriteIconColor = Colors.White.takeIf { game.isFavorite },
        onClick = onClick,
    )

@Suppress("FunctionName")
fun YourGameIndicatorSmallItem_PreviewData(): YourGameIndicatorSmallItem =
    YourGameIndicatorSmallItem(
        wantedImageResource = Icons.gameActionWant,
        wantedBackgroundColor = Colors.WantedGameColor,
        wantedIconColor = Colors.White,
        playedImageResource = Icons.gameActionPlayed,
        playedBackgroundColor = null,
        playedIconColor = null,
        favoriteImageResource = Icons.gameActionFavorite,
        favoriteBackgroundColor = Colors.FavoriteGameColor,
        favoriteIconColor = Colors.White,
        onClick = {},
    )