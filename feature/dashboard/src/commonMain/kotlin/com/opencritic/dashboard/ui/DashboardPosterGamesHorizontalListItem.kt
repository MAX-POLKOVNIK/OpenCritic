package com.opencritic.dashboard.ui

import com.opencritic.dashboard.domain.PosterGame
import com.opencritic.mvvm.ListItem
import kotlinx.collections.immutable.ImmutableList

data class DashboardPosterGamesHorizontalListItem(
    private val popularGames: ImmutableList<PosterGame>,
    private val onClick: (DashboardPosterGameListItem) -> Unit,
) : ListItem<Int> {
    override val id: Int = 1

    val items: List<DashboardPosterGameListItem> =
        popularGames.map {
            DashboardPosterGameListItem(
                game = it,
                onClick = onClick,
            )
        }
}