package com.opencritic.dashboard.ui

import com.opencritic.dashboard.domain.PosterGame
import com.opencritic.games.GameRankModel
import com.opencritic.games.Tier
import com.opencritic.mvvm.ListItem

data class DashboardPosterGameListItem(
    val game: PosterGame,
    private val onClick: (DashboardPosterGameListItem) -> Unit,
) : ListItem<Long> {
    override val id: Long
        get() = game.id

    val nameText: String
        get() = game.name

    val rank: GameRankModel? =
        GameRankModel(game.rank)

    val posterUrl: String
        get() = game.posterUrl

    fun click() =
        onClick(this)
}