package com.opencritic.dashboard.ui

import com.opencritic.games.Tier
import com.opencritic.mvvm.ListItem
import com.opencritic.resources.ImageResource
import com.opencritic.resources.ImageResourceProvider
import com.opencritic.resources.StringProvider

data class DashboardMightyManItemListItem(
    override val id: Tier,
    val imageResource: ImageResource,
    val name: String,
    val description: String,
) : ListItem<Tier>

fun DashboardMightyManItemListItem(
    stringProvider: StringProvider,
    imageResourceProvider: ImageResourceProvider,
    tier: Tier,
): DashboardMightyManItemListItem =
    DashboardMightyManItemListItem(
        id = tier,
        imageResource = when (tier) {
            Tier.Weak -> imageResourceProvider.weakHead
            Tier.Fair -> imageResourceProvider.fairHead
            Tier.Strong -> imageResourceProvider.strongHead
            Tier.Mighty -> imageResourceProvider.mightyHead
        },
        name = when (tier) {
            Tier.Weak -> stringProvider.weak
            Tier.Fair -> stringProvider.fair
            Tier.Strong -> stringProvider.strong
            Tier.Mighty -> stringProvider.mighty
        },
        description = when (tier) {
            Tier.Weak -> stringProvider.weakDescription
            Tier.Fair -> stringProvider.fairDescription
            Tier.Strong -> stringProvider.strongDescription
            Tier.Mighty -> stringProvider.mightyDescription
        }
    )