package com.opencritic.game.your.ui

import com.opencritic.mvvm.ViewModelState
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource

data class YourGameListState(
    val items: List<GameListListItem>,
    val refresh: () -> Unit,
    val isLoginVisible: Boolean,
    val loginText: TextSource,
    val onLoginClick: () -> Unit,
) : ViewModelState

@Suppress("FunctionName")
fun YourGameListState_PreviewData(): YourGameListState =
    YourGameListState(
        items = emptyList(),
        refresh = { },
        isLoginVisible = true,
        loginText = "Login to profile".asTextSource(),
        onLoginClick = {}
    )
