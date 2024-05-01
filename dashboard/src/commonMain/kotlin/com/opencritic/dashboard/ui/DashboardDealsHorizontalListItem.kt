package com.opencritic.dashboard.ui

import com.opencritic.dashboard.domain.GameDeal
import com.opencritic.mvvm.ListItem
import com.opencritic.resources.ImageResourceProvider
import com.opencritic.resources.StringResourceProvider

data class DashboardDealsHorizontalListItem(
    val deals: List<GameDeal>,
    private val stringResourceProvider: StringResourceProvider,
    private val imageResourceProvider: ImageResourceProvider,
    private val onClick: (DashboardDealListItem) -> Unit,
    private val onBuyNowClick: (DashboardDealListItem) -> Unit,
) : ListItem<Int> {
    override val id: Int
        get() = 2

    val items: List<DashboardDealListItem>
        get() = deals.map { deal ->
            DashboardDealListItem(
                gameDeal = deal,
                stringResourceProvider = stringResourceProvider,
                imageResourceProvider = imageResourceProvider,
                onClick = onClick,
                onBuyNowClick = onBuyNowClick,
            )
        }
}