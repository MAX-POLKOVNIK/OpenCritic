package com.opencritic.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.opencritic.dashboard.ui.DashboardContent
import com.opencritic.mvvm.rememberForeverLazyListState
import com.opencritic.resources.defaultPadding

@Composable
fun DashboardStateContent(
    state: DashboardContent,
    modifier: Modifier = Modifier,
    windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass,
) {
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

        when (windowSizeClass.windowWidthSizeClass) {
            WindowWidthSizeClass.COMPACT -> {
                item { DashboardSublistListItem(item = state.recentlyReleased) }
                item { Spacer(modifier = Modifier.size(24.dp)) }
                item { DashboardSublistListItem(item = state.upcomingReleases) }
                item { Spacer(modifier = Modifier.size(24.dp)) }
                item { DashboardSublistListItem(item = state.reviewedToday) }
                item { Spacer(modifier = Modifier.size(24.dp)) }
            }
            WindowWidthSizeClass.MEDIUM -> {
                item {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(defaultPadding)
                    ) {
                        DashboardSublistListItem(
                            item = state.recentlyReleased,
                            modifier = Modifier
                                .weight(1f)
                        )
                        DashboardSublistListItem(
                            item = state.upcomingReleases,
                            modifier = Modifier
                                .weight(1f)
                        )
                    }
                }

                item { Spacer(modifier = Modifier.size(24.dp)) }

                item {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(defaultPadding)
                    ) {
                        DashboardSublistListItem(
                            item = state.reviewedToday,
                            modifier = Modifier
                                .weight(1f)
                        )
                        Spacer(
                            modifier = Modifier
                                .weight(1f)
                        )
                    }
                }

                item { Spacer(modifier = Modifier.size(24.dp)) }
            }
            WindowWidthSizeClass.EXPANDED -> {
                item {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(defaultPadding)
                    ) {
                        DashboardSublistListItem(
                            item = state.recentlyReleased,
                            modifier = Modifier
                                .weight(1f)
                        )
                        DashboardSublistListItem(
                            item = state.upcomingReleases,
                            modifier = Modifier
                                .weight(1f)
                        )
                        DashboardSublistListItem(
                            item = state.reviewedToday,
                            modifier = Modifier
                                .weight(1f)
                        )
                    }
                }
            }
        }

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