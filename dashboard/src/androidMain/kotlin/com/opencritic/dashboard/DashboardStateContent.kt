package com.opencritic.dashboard

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.opencritic.dashboard.ui.DashboardContent
import com.opencritic.mvvm.rememberForeverLazyListState
import com.opencritic.resources.defaultPadding

@Composable
fun DashboardStateContent(state: DashboardContent, modifier: Modifier = Modifier) {
    LazyColumn(
        state = rememberForeverLazyListState(key = "DashboardStateContent"),
        modifier = modifier
    ) {
        item {
            DashboardTitleListItem(
                item = state.popularGamesTitle,
                modifier = Modifier
                    .padding(top = defaultPadding)
            )
        }
        item { DashboardPosterGamesHorizontalListItem(item = state.popularGames) }
        item { Spacer(modifier = Modifier.size(defaultPadding)) }
        item { DashboardTitleListItem(item = state.dealsTitle) }
        item { DashboardDealsHorizontalListItem(item = state.deals) }
        item { Spacer(modifier = Modifier.size(defaultPadding)) }
        item { DashboardSublistListItem(item = state.reviewedToday) }
        item { Spacer(modifier = Modifier.size(24.dp)) }
        item { DashboardSublistListItem(item = state.recentlyReleased) }
        item { Spacer(modifier = Modifier.size(24.dp)) }
        item { DashboardSublistListItem(item = state.upcomingReleases) }
        item { Spacer(modifier = Modifier.size(24.dp)) }
        item { DashboardTitleListItem(item = state.hallOfFameTitle) }
        item { DashboardPosterGamesHorizontalListItem(item = state.hallOfFame) }
        item { Spacer(modifier = Modifier.size(defaultPadding)) }
        item { DashboardMightyManListItem(item = state.whoIsMightyMan) }
        item { Spacer(modifier = Modifier.size(defaultPadding)) }
        item { DashboardTitleListItem(item = state.switchTitle) }
        item { DashboardPosterGamesHorizontalListItem(item = state.switchGames) }
        item { Spacer(modifier = Modifier.size(defaultPadding)) }
        item { DashboardTitleListItem(item = state.xboxTitle) }
        item { DashboardPosterGamesHorizontalListItem(item = state.xboxGames) }
        item { Spacer(modifier = Modifier.size(defaultPadding)) }
        item { DashboardTitleListItem(item = state.playstationTitle) }
        item { DashboardPosterGamesHorizontalListItem(item = state.playstationGames) }
        item { Spacer(modifier = Modifier.size(defaultPadding)) }
    }
}