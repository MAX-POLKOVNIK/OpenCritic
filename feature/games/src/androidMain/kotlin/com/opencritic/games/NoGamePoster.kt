package com.opencritic.games

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.opencritic.resources.MR
import com.opencritic.resources.defaultPadding

@Composable
fun NoGamePoster(
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(Color.LightGray)
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = MR.images.open_critic_logo.drawableResId),
            contentDescription = "",
            colorFilter = ColorFilter.tint(Color.Gray),
            modifier = Modifier
                .padding(defaultPadding)
        )
    }
}

@Preview
@Composable
fun NoGamePoster_Preview() {
    NoGamePoster()
}