package com.opencritic.dashboard.ui

import com.opencritic.games.Tier
import com.opencritic.mvvm.ListItem
import com.opencritic.resources.ImageResource
import com.opencritic.resources.ImageResourceProvider
import com.opencritic.resources.StringResourceProvider
import com.opencritic.resources.get

data class DashboardMightyManItemListItem(
    override val id: Tier,
    val imageResource: ImageResource,
    val name: String,
    val description: String,
) : ListItem<Tier>

fun DashboardMightyManItemListItem(
    stringResourceProvider: StringResourceProvider,
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
            Tier.Weak -> stringResourceProvider.weak.get(stringResourceProvider)
            Tier.Fair -> stringResourceProvider.fair.get(stringResourceProvider)
            Tier.Strong -> stringResourceProvider.strong.get(stringResourceProvider)
            Tier.Mighty -> stringResourceProvider.mighty.get(stringResourceProvider)
        },
        description = when (tier) {
            Tier.Weak -> stringResourceProvider.weakDescription.get(stringResourceProvider)
            Tier.Fair -> stringResourceProvider.fairDescription.get(stringResourceProvider)
            Tier.Strong -> stringResourceProvider.strongDescription.get(stringResourceProvider)
            Tier.Mighty -> stringResourceProvider.mightyDescription.get(stringResourceProvider)
        }
    )