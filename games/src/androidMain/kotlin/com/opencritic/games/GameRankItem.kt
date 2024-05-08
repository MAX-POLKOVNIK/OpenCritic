package com.opencritic.games

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.opencritic.resources.AndroidImageResourceProvider
import com.opencritic.resources.smallPadding

@Composable
fun GameRankItem(
    model: GameRankModel?,
    modifier: Modifier = Modifier,
) {
    if (model != null) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .width(56.dp),
        ) {
            Image(
                painter = painterResource(id = model.headImageResource),
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp, 24.dp)
            )
            Text(
                text = model.scoreText,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = smallPadding)
            )
        }
    } else {
        Text(
            text = "?",
            textAlign = TextAlign.Center,
            modifier = modifier
                .width(56.dp)
        )
    }
}

@Preview
@Composable
fun GameRankItem_Preview() {
    GameRankItem(
        model = GameRankModel(
            headImageResource = AndroidImageResourceProvider().fairHead,
            scoreText = "16",
        )
    )
}