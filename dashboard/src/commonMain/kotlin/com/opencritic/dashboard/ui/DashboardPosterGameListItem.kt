package com.opencritic.dashboard.ui

import com.opencritic.dashboard.domain.PosterGame
import com.opencritic.games.GameRankModel
import com.opencritic.games.Tier
import com.opencritic.mvvm.ListItem
import com.opencritic.resources.ImageResource
import com.opencritic.resources.ImageResourceProvider

data class DashboardPosterGameListItem(
    val game: PosterGame,
    private val imageResourceProvider: ImageResourceProvider,
    private val onClick: (DashboardPosterGameListItem) -> Unit,
) : ListItem<Long> {
    override val id: Long
        get() = game.id

    val nameText: String
        get() = game.name

    val rank: GameRankModel? =
        GameRankModel(imageResourceProvider, game.rank)

    val posterUrl: String
        get() = game.posterUrl

    val isPlayedChecked: Boolean
        get() = false

    val isWantToPlayChecked: Boolean
        get() = false

    fun click() =
        onClick(this)
}