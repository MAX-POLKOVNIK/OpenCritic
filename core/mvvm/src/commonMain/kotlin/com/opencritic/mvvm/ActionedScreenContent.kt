package com.opencritic.mvvm

import com.opencritic.resources.images.IconResource

interface ActionedScreenContent : ScreenContent {
    val isActionVisible: Boolean
    val actionIconResource: IconResource
    val onAction: () -> Unit
}