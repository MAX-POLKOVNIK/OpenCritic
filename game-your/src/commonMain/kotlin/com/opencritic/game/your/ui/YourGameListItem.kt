package com.opencritic.game.your.ui

import com.opencritic.resources.ImageResourceProvider

data class YourGameListItem(
    val text: String,
    val indicatorItem: YourGameIndicatorSmallItem,
    val click: () -> Unit,
)

@Suppress("FunctionName")
fun YourGameListItem_PreviewData(
    imageResourceProvider: ImageResourceProvider,
): YourGameListItem =
    YourGameListItem(
        text = "Some long game name = jdsf sjkadf jksdafjasjkf sjkdafjsadfk",
        indicatorItem = YourGameIndicatorSmallItem_PreviewData(imageResourceProvider),
        click = {}
    )