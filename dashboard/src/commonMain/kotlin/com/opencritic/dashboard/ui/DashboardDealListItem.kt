package com.opencritic.dashboard.ui

import com.opencritic.dashboard.domain.GameDeal
import com.opencritic.mvvm.ListItem
import com.opencritic.resources.MR
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource

data class DashboardDealListItem(
    val gameDeal: GameDeal,
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

    val buyNowText: TextSource
        get() = MR.strings.str_buy_now_on.asTextSource(gameDeal.name)

    fun buyNowClick() =
        onBuyNowClick(this)
}