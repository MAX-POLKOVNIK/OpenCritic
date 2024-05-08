package com.opencritic.dashboard.ui

import com.opencritic.dashboard.domain.GameDeal
import com.opencritic.mvvm.ListItem
import com.opencritic.resources.ImageResourceProvider
import com.opencritic.resources.StringProvider

data class DashboardDealListItem(
    val gameDeal: GameDeal,
    private val stringProvider: StringProvider,
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
        get() = stringProvider.buyNowOnFormatted(gameDeal.name)

    fun buyNowClick() =
        onBuyNowClick(this)
}