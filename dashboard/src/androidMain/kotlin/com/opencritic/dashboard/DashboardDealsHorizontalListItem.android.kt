package com.opencritic.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.opencritic.dashboard.ui.DashboardDealsHorizontalListItem
import com.opencritic.resources.defaultPadding

@Composable
fun DashboardDealsHorizontalListItem(
    item: DashboardDealsHorizontalListItem,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = defaultPadding),
        modifier = modifier,
    ) {
        item.items.forEach {
            item(key = it.id) {
                DashboardDealListItem(item = it)
            }
        }
    }
}