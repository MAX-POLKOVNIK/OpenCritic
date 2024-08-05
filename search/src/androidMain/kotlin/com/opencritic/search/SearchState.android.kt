package com.opencritic.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import com.opencritic.mvvm.ErrorBox
import com.opencritic.mvvm.LoadingBox
import com.opencritic.resources.defaultPadding
import com.opencritic.resources.smallPadding
import com.opencritic.resources.text.text
import com.opencritic.search.ui.SearchItemsState
import com.opencritic.search.ui.SearchState
import com.opencritic.search.ui.SearchState_PreviewData

@Composable
fun SearchState(
    state: SearchState,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }
    var text by remember { mutableStateOf(state.searchText) }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                state.changeSearch(it)
            },
            label = { Text(state.searchHint.text()) },
            singleLine = true,
            modifier = Modifier
                .padding(defaultPadding)
                .fillMaxWidth()
                .focusRequester(focusRequester)
        )

        when (val s = state.searchListItemsState) {
            is SearchItemsState.Content ->
                Column(
                    modifier = modifier
                        .verticalScroll(rememberScrollState())
                ) {
                    s.items.forEach {
                        SearchListItem(
                            item = it,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = defaultPadding)
                                .padding(top = smallPadding)
                        )
                    }
                }

            is SearchItemsState.Empty -> {}
            is SearchItemsState.Error -> ErrorBox(state = s)
            is SearchItemsState.Loading -> LoadingBox(state = s)
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Preview
@Composable
fun SearchState_Preview() {
    SearchState(
        state = SearchState_PreviewData()
    )
}