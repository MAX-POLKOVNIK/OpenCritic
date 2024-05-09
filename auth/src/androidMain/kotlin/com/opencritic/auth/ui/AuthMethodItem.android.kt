package com.opencritic.auth.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.opencritic.resources.defaultPadding

@Composable
fun AuthMethodItem(
    item: AuthMethodItem,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clickable { item.onClick() }
            .padding(horizontal = defaultPadding)
    ) {
        Image(
            painter = painterResource(item.imageResource),
            contentDescription = null,
            modifier = Modifier
                .size(56.dp)
        )

        Text(
            text = item.text,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(start = defaultPadding)
        )
    }
}