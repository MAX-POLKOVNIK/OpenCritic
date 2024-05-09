package com.opencritic.auth.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.opencritic.resources.AndroidImageResourceProvider
import com.opencritic.resources.defaultPadding
import com.opencritic.resources.smallPadding

@Composable
fun AuthStateMethodList(
    state: AuthState.MethodList,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(smallPadding),
        modifier = modifier
            .padding(vertical = defaultPadding),
    ) {
        state.items.forEach {
            AuthMethodItem(it)
        }
    }
}

@Preview
@Composable
fun AuthStateMethodList_Preview() {
    AuthStateMethodList(
        state = AuthStateMethodList_PreviewData(AndroidImageResourceProvider())
    )
}