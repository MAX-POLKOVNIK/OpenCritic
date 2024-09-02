package com.opencritic.halloffame.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.opencritic.resources.defaultPadding

@Composable
fun HallsOfFameContent(
    content: HallsOfFameContent,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = defaultPadding),
        verticalArrangement = Arrangement.spacedBy(defaultPadding)
    ) {
        content.lists.forEach { list ->
            item(key = list.id) {
                HallOfFameGameListListItem(list)
            }
        }
    }
}