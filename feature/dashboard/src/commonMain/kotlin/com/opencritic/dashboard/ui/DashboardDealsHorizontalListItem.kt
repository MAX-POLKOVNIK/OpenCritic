package com.opencritic.dashboard.ui

import com.opencritic.dashboard.domain.GameDeal
import com.opencritic.mvvm.ListItem
import kotlinx.collections.immutable.ImmutableList

data class DashboardDealsHorizontalListItem(
    val deals: ImmutableList<GameDeal>,
    private val onClick: (DashboardDealListItem) -> Unit,
    private val onBuyNowClick: (DashboardDealListItem) -> Unit,
) : ListItem<Int> {
    override val id: Int = 2

    val items: List<DashboardDealListItem> =
        deals.map { deal ->
            DashboardDealListItem(
                gameDeal = deal,
                onClick = onClick,
                onBuyNowClick = onBuyNowClick,
            )
        }
}