package com.opencritic.game.your

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.opencritic.game.your.ui.YourGameListState
import com.opencritic.game.your.ui.YourGameListState_PreviewData
import com.opencritic.mvvm.Spinner
import com.opencritic.resources.defaultPadding
import com.opencritic.resources.smallPadding

@Composable
fun YourGameListState(
    state: YourGameListState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(smallPadding),
        contentPadding = PaddingValues(defaultPadding),
        modifier = modifier
            .fillMaxSize(),
    ) {
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = state.filtersTitleText)

                Spacer(modifier = Modifier.weight(1f))

                Spinner(
                    items = state.filterItems,
                    selectedItem = state.selectedFilterItem,
                    onItemSelected = {
                        state.selectFilterItem(it)
                    },
                    selectedItemFactory = { modifier, item ->
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            modifier = modifier
                                .padding(8.dp)
                        ) {
                            Text(
                                text = item.text,
                            )
                            Icon(
                                imageVector = Icons.Sharp.KeyboardArrowDown,
                                contentDescription = "drop down arrow"
                            )
                        }
                    },
                    dropdownItemFactory = { item, _ ->
                        Text(text = item.text)
                    }
                )
            }
        }

        state.items.forEach {
            item {
                YourGameListItem(item = it)
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