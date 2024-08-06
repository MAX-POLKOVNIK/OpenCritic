package com.opencritic.calendar.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CalendarGameCellItem(
    item: CalendarGameCellItem,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = if (item.isBackgroundVisible) {
        Color.Gray
    } else {
        Color.Transparent
    }

    Box(
        modifier = modifier
            .background(backgroundColor)
            .aspectRatio(9f / 16f)
    ) {
        if (item.currentPoster != null) {
            CalendarGamePosterCellItem(item = item.currentPoster)
        }

        Box(
            modifier = Modifier
        ) {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = item.dayText)
            }

            if (item.isDotsVisible) {
                Row(
                    Modifier
                        .wrapContentHeight()
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 4.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(item.dotsCount) { iteration ->
                        val color = if (item.currentPosterIndex == iteration) {
                            Color.DarkGray
                        } else {
                            Color.LightGray
                        }
                        Box(
                            modifier = Modifier
                                .padding(1.dp)
                                .clip(CircleShape)
                                .background(color)
                                .size(3.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CalendarGameCellItem_Preview() {
    CalendarGameCellItem(
        item = CalendarGameCellItem_PreviewData(),
    )
}