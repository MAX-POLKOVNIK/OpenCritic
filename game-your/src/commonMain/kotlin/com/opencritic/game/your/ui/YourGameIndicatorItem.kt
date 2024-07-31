package com.opencritic.game.your.ui

import com.opencritic.game.your.domain.YourGame
import com.opencritic.game.your.domain.YourGameAction
import com.opencritic.resources.Color
import com.opencritic.resources.Colors
import com.opencritic.resources.IconResource
import com.opencritic.resources.Icons
import com.opencritic.resources.MR
import com.opencritic.resources.StringProvider
import com.opencritic.resources.getString

data class YourGameIndicatorItem(
    val wantedImageResource: IconResource,
    val wantedText: String,
    val wantedTextColor: Color?,
    val wantedBackgroundColor: Color,
    val playedImageResource: IconResource,
    val playedText: String,
    val playedTextColor: Color?,
    val playedBackgroundColor: Color,
    val favoriteImageResource: IconResource,
    val favoriteText: String,
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
    stringProvider: StringProvider,
    onClick: (YourGameAction) -> Unit,
): YourGameIndicatorItem =
    YourGameIndicatorItem(
        wantedImageResource = Icons.gameActionWant,
        wantedText = stringProvider.getString(MR.strings.str_game_action_want),
        wantedTextColor = Colors.White.takeIf { game.isWanted },
        wantedBackgroundColor = if (game.isWanted) Colors.WantedGameColor else Colors.Transparent,
        playedImageResource = Icons.gameActionPlayed,
        playedText = stringProvider.getString(MR.strings.str_game_action_played),
        playedTextColor = Colors.White.takeIf { game.isPlayed },
        playedBackgroundColor = if (game.isPlayed) Colors.PlayedGameColor else Colors.Transparent,
        favoriteImageResource = Icons.gameActionFavorite,
        favoriteText = stringProvider.getString(MR.strings.str_game_action_favorite),
        favoriteTextColor = Colors.White.takeIf { game.isFavorite },
        favoriteBackgroundColor = if (game.isFavorite) Colors.FavoriteGameColor else Colors.Transparent,
        onClick = onClick,
    )

@Suppress("FunctionName")
fun YourGameIndicatorItem_PreviewData(): YourGameIndicatorItem =
    YourGameIndicatorItem(
        wantedImageResource = Icons.gameActionWant,
        wantedText = "Want",
        wantedTextColor = Colors.White,
        wantedBackgroundColor = Colors.WantedGameColor,
        playedImageResource = Icons.gameActionPlayed,
        playedText = "Played",
        playedTextColor = null,
        playedBackgroundColor = Colors.Transparent,
        favoriteImageResource = Icons.gameActionFavorite,
        favoriteText = "Favorite",
        favoriteTextColor = Colors.White,
        favoriteBackgroundColor = Colors.FavoriteGameColor,
        onClick = {},
    )