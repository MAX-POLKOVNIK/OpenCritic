package com.opencritic.dashboard.ui

import com.opencritic.games.Tier
import com.opencritic.mvvm.ListItem
import com.opencritic.resources.ImageResourceProvider
import com.opencritic.resources.StringResourceProvider
import com.opencritic.resources.get

data class DashboardMightyManListItem(
    override val id: Unit = Unit,
    val title: String,
    val description: String,
    val colorDescription: String,
    val items: List<DashboardMightyManItemListItem>,
) : ListItem<Unit>


fun DashboardMightyManListItem(
    stringResourceProvider: StringResourceProvider,
    imageResourceProvider: ImageResourceProvider,
): DashboardMightyManListItem =
    DashboardMightyManListItem(
        title = stringResourceProvider.whoIsMightyMan.get(stringResourceProvider),
        description = stringResourceProvider.whoIsMightyManDescription.get(stringResourceProvider),
        colorDescription = stringResourceProvider.whoIsMightyManColorDescription.get(stringResourceProvider),
        items = Tier.entries.map {
            DashboardMightyManItemListItem(
                stringResourceProvider = stringResourceProvider,
                imageResourceProvider = imageResourceProvider,
                tier = it,
            )
        }
    )