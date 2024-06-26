package com.opencritic.games.details.reviews

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.opencritic.games.details.ui.IconTextItem
import com.opencritic.resources.smallPadding

@Composable
fun IconTextItem(
    item: IconTextItem,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = "",
            modifier = Modifier
                .size(24.dp, 24.dp)
        )

        Text(
            text = item.text,
            modifier = Modifier
                .padding(start = smallPadding)
        )
    }
}