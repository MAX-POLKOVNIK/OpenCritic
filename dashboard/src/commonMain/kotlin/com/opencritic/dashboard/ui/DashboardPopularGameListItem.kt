package com.opencritic.dashboard.ui

import com.opencritic.dashboard.domain.PopularGame
import com.opencritic.games.Tier
import com.opencritic.mvvm.ListItem
import com.opencritic.resources.ImageResource
import com.opencritic.resources.ImageResourceProvider

data class DashboardPopularGameListItem(
    val game: PopularGame,
    private val imageResourceProvider: ImageResourceProvider,
    private val onClick: (DashboardPopularGameListItem) -> Unit,
) : ListItem<Long> {
    override val id: Long
        get() = game.id

    val nameText: String
        get() = game.name

    val scoreText: String
        get() = game.score.toString()

    val headImageResource: ImageResource
        get() = when (game.tier) {
            Tier.Weak -> imageResourceProvider.weakHead
            Tier.Fair -> imageResourceProvider.fairHead
            Tier.Strong -> imageResourceProvider.strongHead
            Tier.Mighty -> imageResourceProvider.mightyHead
        }

    val posterUrl: String
        get() = game.posterUrl

    val isPlayedChecked: Boolean
        get() = false

    val isWantToPlayChecked: Boolean
        get() = false

    fun click() =
        onClick(this)
}