package com.opencritic.dashboard.ui

import com.opencritic.dashboard.domain.GameDeal
import com.opencritic.mvvm.ListItem
import com.opencritic.resources.MR
import com.opencritic.resources.StringProvider
import com.opencritic.resources.getFormattedString

data class DashboardDealListItem(
    val gameDeal: GameDeal,
    private val stringProvider: StringProvider,
    private val onClick: (DashboardDealListItem) -> Unit,
    private val onBuyNowClick: (DashboardDealListItem) -> Unit,
) : ListItem<Long> {
    override val id: Long
        get() = gameDeal.game.id

    val gameItem: DashboardPosterGameListItem
        get() = DashboardPosterGameListItem(
            game = gameDeal.game,
            onClick = { onClick(this) }
        )

    val priceText: String
        get() = "$${gameDeal.price}"

    val buyNowText: String
        get() = stringProvider.getFormattedString(MR.strings.str_buy_now_on, gameDeal.name)

    fun buyNowClick() =
        onBuyNowClick(this)
}