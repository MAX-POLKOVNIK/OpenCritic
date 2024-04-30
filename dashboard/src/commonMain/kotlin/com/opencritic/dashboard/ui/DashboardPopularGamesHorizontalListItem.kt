package com.opencritic.dashboard.ui

import com.opencritic.dashboard.domain.PopularGame
import com.opencritic.mvvm.ListItem
import com.opencritic.resources.ImageResourceProvider

data class DashboardPopularGamesHorizontalListItem(
    private val popularGames: List<PopularGame>,
    private val imageResourceProvider: ImageResourceProvider,
    private val onClick: (DashboardPopularGameListItem) -> Unit,
) : ListItem<Int> {
    override val id: Int
        get() = 1

    val items: List<DashboardPopularGameListItem> =
        popularGames.map {
            DashboardPopularGameListItem(
                game = it,
                imageResourceProvider = imageResourceProvider,
                onClick = onClick,
            )
        }
}