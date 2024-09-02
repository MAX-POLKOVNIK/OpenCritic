package com.opencritic.dashboard.ui

import com.opencritic.dashboard.domain.PosterGame
import com.opencritic.mvvm.ListItem

data class DashboardPosterGamesHorizontalListItem(
    private val popularGames: List<PosterGame>,
    private val onClick: (DashboardPosterGameListItem) -> Unit,
) : ListItem<Int> {
    override val id: Int
        get() = 1

    val items: List<DashboardPosterGameListItem> =
        popularGames.map {
            DashboardPosterGameListItem(
                game = it,
                onClick = onClick,
            )
        }
}