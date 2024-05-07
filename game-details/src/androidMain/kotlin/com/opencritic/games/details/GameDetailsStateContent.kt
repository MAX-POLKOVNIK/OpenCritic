package com.opencritic.games.details

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.opencritic.games.details.ui.GameDetailsState
import com.opencritic.games.details.ui.GameDetailsStateContent_PreviewData
import com.opencritic.resources.AndroidImageResourceProvider

@Composable
fun GameDetailsStateContent(state: GameDetailsState.Content, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
    ) {

    }
}

@Composable
@Preview
fun GameDetailsStateContent_Preview() {
    GameDetailsStateContent(
        state = GameDetailsStateContent_PreviewData(AndroidImageResourceProvider())
    )
}