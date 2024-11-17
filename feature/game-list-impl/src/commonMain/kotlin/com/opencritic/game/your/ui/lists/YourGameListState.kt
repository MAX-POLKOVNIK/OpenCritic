package com.opencritic.game.your.ui.lists

import com.opencritic.mvvm.ActionedScreenContent
import com.opencritic.mvvm.ScreenContent
import com.opencritic.mvvm.ViewModelState
import com.opencritic.resources.images.IconResource
import com.opencritic.resources.images.Icons
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class YourGameListState(
    val items: ImmutableList<GameListListItem>,
    val refresh: () -> Unit,
    val isLoginVisible: Boolean,
    val loginText: TextSource,
    val onLoginClick: () -> Unit,
    val useOfflineText: TextSource,
    val onUseOfflineClick: () -> Unit,
    override val isActionVisible: Boolean,
    override val actionIconResource: IconResource,
    override val onAction: () -> Unit,
) : ActionedScreenContent

@Suppress("FunctionName")
fun YourGameListState_PreviewData(): YourGameListState =
    YourGameListState(
        items = persistentListOf(),
        refresh = { },
        isLoginVisible = true,
        loginText = "Login to profile".asTextSource(),
        onLoginClick = {},
        useOfflineText = "Use offline mode".asTextSource(),
        onUseOfflineClick = {},
        isActionVisible = true,
        actionIconResource = Icons.info,
        onAction = {}
    )
