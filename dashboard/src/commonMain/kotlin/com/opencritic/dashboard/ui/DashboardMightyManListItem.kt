package com.opencritic.dashboard.ui

import com.opencritic.games.Tier
import com.opencritic.mvvm.ListItem
import com.opencritic.resources.ImageResourceProvider
import com.opencritic.resources.StringProvider

data class DashboardMightyManListItem(
    override val id: Unit = Unit,
    val title: String,
    val description: String,
    val colorDescription: String,
    val items: List<DashboardMightyManItemListItem>,
) : ListItem<Unit>


fun DashboardMightyManListItem(
    stringProvider: StringProvider,
    imageResourceProvider: ImageResourceProvider,
): DashboardMightyManListItem =
    DashboardMightyManListItem(
        title = stringProvider.whoIsMightyMan,
        description = stringProvider.whoIsMightyManDescription,
        colorDescription = stringProvider.whoIsMightyManColorDescription,
        items = Tier.entries.map {
            DashboardMightyManItemListItem(
                stringProvider = stringProvider,
                imageResourceProvider = imageResourceProvider,
                tier = it,
            )
        }
    )