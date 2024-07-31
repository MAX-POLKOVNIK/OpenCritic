package com.opencritic.dashboard.ui

import com.opencritic.games.Tier
import com.opencritic.mvvm.ListItem
import com.opencritic.resources.StringProvider
import com.opencritic.resources.StringRes
import com.opencritic.resources.getString

data class DashboardMightyManListItem(
    override val id: Unit = Unit,
    val title: String,
    val description: String,
    val colorDescription: String,
    val items: List<DashboardMightyManItemListItem>,
) : ListItem<Unit>


fun DashboardMightyManListItem(
    stringProvider: StringProvider,
): DashboardMightyManListItem =
    DashboardMightyManListItem(
        title = stringProvider.getString(StringRes.who_is_mighty_man),
        description = stringProvider.getString(StringRes.who_is_mighty_man_description),
        colorDescription = stringProvider.getString(StringRes.who_is_mighty_man_color_description),
        items = Tier.entries.map {
            DashboardMightyManItemListItem(
                stringProvider = stringProvider,
                tier = it,
            )
        }
    )