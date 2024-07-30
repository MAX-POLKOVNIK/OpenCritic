package com.opencritic.dashboard.ui

import com.opencritic.games.Tier
import com.opencritic.mvvm.ListItem
import com.opencritic.resources.ImageResource
import com.opencritic.resources.ImageResourceProvider
import com.opencritic.resources.MR
import com.opencritic.resources.StringProvider
import com.opencritic.resources.StringRes
import com.opencritic.resources.getString

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
            Tier.Weak -> stringProvider.getString(StringRes.str_weak)
            Tier.Fair -> stringProvider.getString(StringRes.str_fair)
            Tier.Strong -> stringProvider.getString(StringRes.str_strong)
            Tier.Mighty -> stringProvider.getString(StringRes.str_mighty)
        },
        description = when (tier) {
            Tier.Weak -> stringProvider.getString(StringRes.str_weak_description)
            Tier.Fair -> stringProvider.getString(StringRes.str_fair_description)
            Tier.Strong -> stringProvider.getString(StringRes.str_strong_description)
            Tier.Mighty -> stringProvider.getString(StringRes.str_mighty_description)
        }
    )