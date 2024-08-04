package com.opencritic.game.your.ui.list

import com.opencritic.game.your.domain.GameInList
import com.opencritic.games.GameRank
import com.opencritic.games.GameRankModel
import com.opencritic.games.Tier
import com.opencritic.resources.images.SharedImageResource
import com.opencritic.resources.images.SharedImages

data class GameRowListItem(
    val id: Long,
    val name: String,
    val posterUrl: String,
    val rank: GameRankModel?,
    val onClick: () -> Unit,
)

fun GameRowListItem(gameInList: GameInList, onClick: () -> Unit): GameRowListItem =
    GameRowListItem(
        id = gameInList.id,
        name = gameInList.name,
        posterUrl = gameInList.posterUrl,
        rank =  GameRankModel(gameInList.rank),
        onClick = onClick
    )

fun GameRowListItem_PreviewData(): GameRowListItem =
    GameRowListItem(
        id = 0,
        name = "Some game name",
        posterUrl = "https://img.opencritic.com/game/undefined/WD3FWqxB.jpg",
        rank = GameRankModel(GameRank(Tier.Fair, 60)),
        onClick = {},
    )