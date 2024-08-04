package com.opencritic.game.your.ui.lists

import com.opencritic.game.your.domain.YourGame
import com.opencritic.game.your.domain.YourGameAction
import com.opencritic.resources.colors.Color
import com.opencritic.resources.colors.Colors
import com.opencritic.resources.images.IconResource
import com.opencritic.resources.images.Icons
import com.opencritic.resources.MR
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource

data class YourGameIndicatorItem(
    val wantedImageResource: IconResource,
    val wantedText: TextSource,
    val wantedTextColor: Color?,
    val wantedBackgroundColor: Color,
    val playedImageResource: IconResource,
    val playedText: TextSource,
    val playedTextColor: Color?,
    val playedBackgroundColor: Color,
    val favoriteImageResource: IconResource,
    val favoriteText: TextSource,
    val favoriteTextColor: Color?,
    val favoriteBackgroundColor: Color,
    private val onClick: (YourGameAction) -> Unit,
) {
    fun wantedClick() = onClick(YourGameAction.Want)
    fun playedClick() = onClick(YourGameAction.Played)
    fun favoriteClick() = onClick(YourGameAction.Favorite)
}

fun YourGameIndicatorItem(
    game: YourGame,
    onClick: (YourGameAction) -> Unit,
): YourGameIndicatorItem =
    YourGameIndicatorItem(
        wantedImageResource = Icons.gameActionWant,
        wantedText = MR.strings.str_game_action_want.asTextSource(),
        wantedTextColor = Colors.White.takeIf { game.isWanted },
        wantedBackgroundColor = if (game.isWanted) Colors.WantedGameColor else Colors.Transparent,
        playedImageResource = Icons.gameActionPlayed,
        playedText = MR.strings.str_game_action_played.asTextSource(),
        playedTextColor = Colors.White.takeIf { game.isPlayed },
        playedBackgroundColor = if (game.isPlayed) Colors.PlayedGameColor else Colors.Transparent,
        favoriteImageResource = Icons.gameActionFavorite,
        favoriteText = MR.strings.str_game_action_favorite.asTextSource(),
        favoriteTextColor = Colors.White.takeIf { game.isFavorite },
        favoriteBackgroundColor = if (game.isFavorite) Colors.FavoriteGameColor else Colors.Transparent,
        onClick = onClick,
    )

@Suppress("FunctionName")
fun YourGameIndicatorItem_PreviewData(): YourGameIndicatorItem =
    YourGameIndicatorItem(
        wantedImageResource = Icons.gameActionWant,
        wantedText = "Want".asTextSource(),
        wantedTextColor = Colors.White,
        wantedBackgroundColor = Colors.WantedGameColor,
        playedImageResource = Icons.gameActionPlayed,
        playedText = "Played".asTextSource(),
        playedTextColor = null,
        playedBackgroundColor = Colors.Transparent,
        favoriteImageResource = Icons.gameActionFavorite,
        favoriteText = "Favorite".asTextSource(),
        favoriteTextColor = Colors.White,
        favoriteBackgroundColor = Colors.FavoriteGameColor,
        onClick = {},
    )