package com.opencritic.game.your.ui

data class YourGameListItem(
    val text: String,
    val indicatorItem: YourGameIndicatorSmallItem,
    val click: () -> Unit,
)

@Suppress("FunctionName")
fun YourGameListItem_PreviewData(): YourGameListItem =
    YourGameListItem(
        text = "Some long game name = jdsf sjkadf jksdafjasjkf sjkdafjsadfk",
        indicatorItem = YourGameIndicatorSmallItem_PreviewData(),
        click = {}
    )