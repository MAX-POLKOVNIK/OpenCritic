package com.opencritic.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.opencritic.dashboard.domain.PosterGame
import com.opencritic.dashboard.ui.DashboardPosterGamesHorizontalListItem
import com.opencritic.games.GameRank
import com.opencritic.games.Tier
import com.opencritic.resources.defaultPadding

@Composable
fun DashboardPosterGamesHorizontalListItem(
    item: DashboardPosterGamesHorizontalListItem,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = defaultPadding),
        modifier = modifier,
    ) {
        item.items.forEach {
            item(key = it.id) {
                DashboardPosterGameListItem(item = it)
            }
        }
    }
}

@Composable
@Preview
fun DashboardPosterGamesHorizontalListItem_Preview() {
    DashboardPosterGamesHorizontalListItem(
        item = DashboardPosterGamesHorizontalListItem(
            popularGames = listOf(
                PosterGame(
                    id = 1,
                    name = "Test some long name",
                    posterUrl = "https://img.opencritic.com/game/14353/o/cDwMyHmW.jpg",
                    rank = GameRank(tier = Tier.Fair, score = 32)
                ),
                PosterGame(
                    id = 2,
                    name = "Test some long name",
                    posterUrl = "https://img.opencritic.com/game/14353/o/cDwMyHmW.jpg",
                    rank = GameRank(tier = Tier.Fair, score = 32)
                ),
               PosterGame(
                    id = 3,
                    name = "Test some long name",
                    posterUrl = "https://img.opencritic.com/game/14353/o/cDwMyHmW.jpg",
                    rank = GameRank(tier = Tier.Fair, score = 32)
               ),
            ),
            onClick = {}
        )
    )
}