package com.opencritic.game.your.ui

import com.opencritic.game.your.domain.YourGame
import com.opencritic.game.your.domain.YourGameAction
import com.opencritic.resources.Color
import com.opencritic.resources.Colors
import com.opencritic.resources.ImageResource
import com.opencritic.resources.ImageResourceProvider

data class YourGameIndicatorSmallItem(
    val wantedImageResource: ImageResource,
    val wantedBackgroundColor: Color?,
    val wantedIconColor: Color?,
    val playedImageResource: ImageResource,
    val playedBackgroundColor: Color?,
    val playedIconColor: Color?,
    val favoriteImageResource: ImageResource,
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
    imageResourceProvider: ImageResourceProvider,
    onClick: (YourGameAction) -> Unit,
): YourGameIndicatorSmallItem =
    YourGameIndicatorSmallItem(
        wantedImageResource = imageResourceProvider.gameActionWant,
        wantedBackgroundColor = Colors.WantedGameColor.takeIf { game.isWanted },
        wantedIconColor = Colors.White.takeIf { game.isWanted },
        playedImageResource = imageResourceProvider.gameActionPlayed,
        playedBackgroundColor = Colors.PlayedGameColor.takeIf { game.isPlayed },
        playedIconColor = Colors.White.takeIf { game.isPlayed },
        favoriteImageResource = imageResourceProvider.gameActionFavorite,
        favoriteBackgroundColor = Colors.FavoriteGameColor.takeIf { game.isFavorite },
        favoriteIconColor = Colors.White.takeIf { game.isFavorite },
        onClick = onClick,
    )

@Suppress("FunctionName")
fun YourGameIndicatorSmallItem_PreviewData(
    imageResourceProvider: ImageResourceProvider,
): YourGameIndicatorSmallItem =
    YourGameIndicatorSmallItem(
        wantedImageResource = imageResourceProvider.gameActionWant,
        wantedBackgroundColor = Colors.WantedGameColor,
        wantedIconColor = Colors.White,
        playedImageResource = imageResourceProvider.gameActionPlayed,
        playedBackgroundColor = null,
        playedIconColor = null,
        favoriteImageResource = imageResourceProvider.gameActionFavorite,
        favoriteBackgroundColor = Colors.FavoriteGameColor,
        favoriteIconColor = Colors.White,
        onClick = {},
    )