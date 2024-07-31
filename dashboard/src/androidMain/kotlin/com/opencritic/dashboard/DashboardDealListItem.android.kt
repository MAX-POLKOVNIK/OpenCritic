package com.opencritic.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.opencritic.dashboard.ui.DashboardDealListItem
import com.opencritic.resources.text.text

@Composable
fun DashboardDealListItem(
    item: DashboardDealListItem,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        DashboardPosterGameListItem(item = item.gameItem)
        TextButton(
            onClick = item::buyNowClick,
            modifier = Modifier
                .widthIn(0.dp, 128.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = item.priceText)
                Text(
                    textAlign = TextAlign.Center,
                    text = item.buyNowText.text(),
                    modifier = Modifier
                        .defaultMinSize(minHeight = 48.dp)
                )
            }
        }
    }
}

//@Composable
//@Preview
//fun DashboardDealListItem_Preview() {
//    DashboardDealListItem(
//        item = DashboardDealListItem(
//            gameDeal = GameDeal(
//                game = PosterGame(
//                    id = 1,
//                    name = "Test some long name",
//                    posterUrl = "https://img.opencritic.com/game/14353/o/cDwMyHmW.jpg",
//                    rank = GameRank(Tier.Fair, 32)
//                ),
//                name = "Amazon",
//                price = 49.9f,
//                externalUrl = "",
//            ),
//            onClick = {},
//            imageResourceProvider = Android
//        )
//    )
//}