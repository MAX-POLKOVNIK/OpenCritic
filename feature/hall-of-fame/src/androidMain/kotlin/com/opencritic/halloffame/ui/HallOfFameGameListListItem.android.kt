package com.opencritic.halloffame.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.opencritic.resources.defaultPadding
import com.opencritic.resources.text.text

@Composable
fun HallOfFameGameListListItem(
    item: HallOfFameGameListListItem,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier,
    ) {
        Text(
            text = item.yearText.text(),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(defaultPadding)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = defaultPadding),
        ) {
            item.games.forEach {
                item(key = it.id) {
                    HallOfFameGameListItem(item = it)
                }
            }
        }
    }
}

@Preview
@Composable
fun HallOfFameGameListListItem_Preview() {
    HallOfFameGameListListItem(
        item = HallOfFameGameListListItem_PreviewData()
    )
}