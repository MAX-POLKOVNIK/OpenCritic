package com.opencritic.games.details

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import com.opencritic.games.Tier
import com.opencritic.games.details.ui.RankCircleIndicatorItem
import com.opencritic.games.details.ui.createCriticsRecommendIndicator
import com.opencritic.resources.colors.toCompose

@Composable
fun RankCircleIndicatorItem(
    item: RankCircleIndicatorItem,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .background(item.backgroundColor.toCompose())
        )

        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val start = ((1.0 - item.progress) / 2) * 360
            val angle = item.progress * 360

            val offsetX = size.width * 0.1f
            val offsetY = size.height * 0.1f

            val brush = if (item.colors.size > 1) {
                Brush.sweepGradient(
                    colors = item.colors.map { it.toCompose() }
                )
            } else {
                Brush.linearGradient(
                    colors = List(2) { item.colors.first().toCompose() }
                )
            }

            drawArc(
                startAngle = start.toFloat(),
                sweepAngle = angle,
                useCenter = false,
                brush = brush,
                style = Stroke(
                    width = size.width * 0.1f,
                    cap = StrokeCap.Round
                ),
                size = Size(size.width * 0.8f, size.height * 0.8f),
                topLeft = Offset(offsetX,offsetY),
            )
        }

        Text(
            text = item.scoreText,
            color = Color.White
        )
    }
}

@Preview(widthDp = 56, heightDp = 56)
@Composable
fun RankCircleIndicatorItem_Preview() {
    RankCircleIndicatorItem(
        item = createCriticsRecommendIndicator(Tier.Mighty, 75f)
    )
}