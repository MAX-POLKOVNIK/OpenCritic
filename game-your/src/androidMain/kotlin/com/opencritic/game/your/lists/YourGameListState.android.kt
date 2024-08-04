package com.opencritic.game.your.lists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.opencritic.game.your.ui.lists.YourGameListState
import com.opencritic.game.your.ui.lists.YourGameListState_PreviewData
import com.opencritic.resources.defaultPadding
import com.opencritic.resources.smallPadding
import com.opencritic.resources.text.text

@Composable
fun YourGameListState(
    state: YourGameListState,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize(),
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(smallPadding),
            contentPadding = PaddingValues(defaultPadding),
            modifier = modifier
                .fillMaxSize(),
        ) {
            state.items.forEach {
                item {
                    GameListListItem(item = it)
                }
            }
        }

        if (state.isLoginVisible) {
            Button(onClick = state.onLoginClick) {
                Text(text = state.loginText.text())
            }
        }
    }

    LaunchedEffect(Unit) {
        state.refresh()
    }
}

@Preview
@Composable
fun YourGameListState_Preview() {
    YourGameListState(
        state = YourGameListState_PreviewData()
    )
}