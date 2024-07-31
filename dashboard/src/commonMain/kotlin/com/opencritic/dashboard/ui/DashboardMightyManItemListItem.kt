package com.opencritic.dashboard.ui

import com.opencritic.games.Tier
import com.opencritic.mvvm.ListItem
import com.opencritic.resources.images.SharedImageResource
import com.opencritic.resources.images.SharedImages
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource

data class DashboardMightyManItemListItem(
    override val id: Tier,
    val imageResource: SharedImageResource,
    val name: TextSource,
    val description: TextSource,
) : ListItem<Tier>

fun DashboardMightyManItemListItem(
    tier: Tier,
): DashboardMightyManItemListItem =
    DashboardMightyManItemListItem(
        id = tier,
        imageResource = when (tier) {
            Tier.Weak -> SharedImages.weakHead
            Tier.Fair -> SharedImages.fairHead
            Tier.Strong -> SharedImages.strongHead
            Tier.Mighty -> SharedImages.mightyHead
        },
        name = when (tier) {
            Tier.Weak -> StringRes.str_weak.asTextSource()
            Tier.Fair -> StringRes.str_fair.asTextSource()
            Tier.Strong -> StringRes.str_strong.asTextSource()
            Tier.Mighty -> StringRes.str_mighty.asTextSource()
        },
        description = when (tier) {
            Tier.Weak -> StringRes.str_weak_description.asTextSource()
            Tier.Fair -> StringRes.str_fair_description.asTextSource()
            Tier.Strong -> StringRes.str_strong_description.asTextSource()
            Tier.Mighty -> StringRes.str_mighty_description.asTextSource()
        }
    )