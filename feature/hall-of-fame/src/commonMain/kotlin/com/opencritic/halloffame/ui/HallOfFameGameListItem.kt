package com.opencritic.halloffame.ui

import com.opencritic.games.GameRankModel
import com.opencritic.halloffame.domain.HallOfFameGame
import com.opencritic.mvvm.ListItem

data class HallOfFameGameListItem(
    override val id: Long,
    val nameText: String,
    val rank: GameRankModel?,
    val posterUrl: String,
    val onClick: () -> Unit,
) : ListItem<Long>

fun HallOfFameGameListItem(
    game: HallOfFameGame,
    onClick: () -> Unit,
): HallOfFameGameListItem =
    HallOfFameGameListItem(
        id = game.id,
        nameText = game.name,
        rank = GameRankModel(game.rank),
        posterUrl = game.posterImageUrl,
        onClick = onClick,
    )

@Suppress("FunctionName")
fun HallOfFameGameListItem_PreviewData(): HallOfFameGameListItem =
    HallOfFameGameListItem(
        id = 1,
        nameText = "Some game",
        rank = null,
        posterUrl = "",
        onClick = {}
    )

