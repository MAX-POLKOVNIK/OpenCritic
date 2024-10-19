package com.opencritic.dashboard.ui

import com.opencritic.games.Tier
import com.opencritic.mvvm.ListItem
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource

data class DashboardMightyManListItem(
    override val id: Unit = Unit,
    val title: TextSource,
    val description: TextSource,
    val colorDescription: TextSource,
    val items: List<DashboardMightyManItemListItem>,
) : ListItem<Unit>


fun DashboardMightyManListItem(): DashboardMightyManListItem =
    DashboardMightyManListItem(
        title = StringRes.who_is_mighty_man.asTextSource(),
        description = StringRes.who_is_mighty_man_description.asTextSource(),
        colorDescription = StringRes.who_is_mighty_man_color_description.asTextSource(),
        items = Tier.entries.map {
            DashboardMightyManItemListItem(
                tier = it,
            )
        }
    )