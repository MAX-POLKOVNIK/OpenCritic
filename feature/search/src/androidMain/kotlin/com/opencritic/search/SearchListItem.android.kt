package com.opencritic.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.opencritic.resources.defaultPadding
import com.opencritic.resources.smallPadding
import com.opencritic.resources.text.text
import com.opencritic.resources.colors.toCompose
import com.opencritic.search.ui.SearchListItem
import com.opencritic.search.ui.SearchListItem_PreviewData

@Composable
fun SearchListItem(
    item: SearchListItem,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clickable { item.click() }
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = item.kindColor.toCompose(),
            )
        ) {
            Text(
                text = item.kindText.text(),
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .padding(smallPadding)
            )
        }

        Text(
            text = item.nameText,
            modifier = Modifier
                .padding(horizontal = defaultPadding)
        )
    }
}

@Preview
@Composable
fun SearchListItem_Preview() {
    SearchListItem(item = SearchListItem_PreviewData())
}