package com.opencritic.dashboard.ui

import com.opencritic.dashboard.domain.GameDeal
import com.opencritic.mvvm.ListItem
import com.opencritic.resources.ImageResourceProvider
import com.opencritic.resources.StringResourceProvider
import com.opencritic.resources.get

data class DashboardDealListItem(
    val gameDeal: GameDeal,
    private val stringResourceProvider: StringResourceProvider,
    private val imageResourceProvider: ImageResourceProvider,
    private val onClick: (DashboardDealListItem) -> Unit,
    private val onBuyNowClick: (DashboardDealListItem) -> Unit,
) : ListItem<Long> {
    override val id: Long
        get() = gameDeal.game.id

    val gameItem: DashboardPosterGameListItem
        get() = DashboardPosterGameListItem(
            game = gameDeal.game,
            imageResourceProvider = imageResourceProvider,
            onClick = { onClick(this) }
        )

    val priceText: String
        get() = "$${gameDeal.price}"

    val buyNowText: String
        get() = stringResourceProvider.buyNowOnFormatted.get(
            stringResourceProvider,
            gameDeal.name
        )

    fun buyNowClick() =
        onBuyNowClick(this)
}