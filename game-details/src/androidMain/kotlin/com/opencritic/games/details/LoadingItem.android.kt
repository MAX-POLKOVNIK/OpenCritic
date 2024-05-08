package com.opencritic.games.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.opencritic.games.details.ui.LoadingItem
import com.opencritic.resources.defaultPadding

@Composable
fun LoadingItem(
    item: LoadingItem,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(48.dp)
        )
        Text(
            text = "Loading...",
            modifier = Modifier
                .padding(start = defaultPadding)
        )
    }
}