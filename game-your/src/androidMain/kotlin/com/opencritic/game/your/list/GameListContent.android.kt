package com.opencritic.game.your.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.opencritic.game.your.ui.list.GameListContent
import com.opencritic.game.your.ui.list.GameListContent_PreviewData
import com.opencritic.resources.defaultPadding

@Composable
fun GameListContent(
    content: GameListContent,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(defaultPadding),
        verticalArrangement = Arrangement.spacedBy(defaultPadding),
        modifier = modifier,
    ) {
        content.items.forEach {
            item(key = it.id) {
                GameRowListItem(item = it)
            }
        }
    }
}

@Preview
@Composable
fun GameListContent_Preview() {
    GameListContent(
        content = GameListContent_PreviewData()
    )
}