package com.opencritic.games.details

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.opencritic.games.details.ui.ReviewBriefListItem
import com.opencritic.resources.defaultPadding

@Composable
fun ReviewBriefListItem(
    item: ReviewBriefListItem,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = defaultPadding)
    ) {
        Text(text = item.nameText)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = item.scoreText)
    }
}

@Preview
@Composable
fun ReviewBriefListItem_Preview() {
    ReviewBriefListItem(
        item = ReviewBriefListItem(
            nameText = "IGN",
            scoreText = "100 / 100"
        )
    )
}