package com.opencritic.dashboard.ui

import com.opencritic.dashboard.domain.GameDeal
import com.opencritic.mvvm.ListItem
import com.opencritic.resources.StringProvider

data class DashboardDealsHorizontalListItem(
    val deals: List<GameDeal>,
    private val stringProvider: StringProvider,
    private val onClick: (DashboardDealListItem) -> Unit,
    private val onBuyNowClick: (DashboardDealListItem) -> Unit,
) : ListItem<Int> {
    override val id: Int
        get() = 2

    val items: List<DashboardDealListItem>
        get() = deals.map { deal ->
            DashboardDealListItem(
                gameDeal = deal,
                stringProvider = stringProvider,
                onClick = onClick,
                onBuyNowClick = onBuyNowClick,
            )
        }
}