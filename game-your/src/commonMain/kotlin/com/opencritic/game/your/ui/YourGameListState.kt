package com.opencritic.game.your.ui

import com.opencritic.mvvm.ViewModelState

data class YourGameListState(
    val filtersTitleText: String,
    val selectedFilterItem: YourGameFilterItem,
    val filterItems: List<YourGameFilterItem>,
    val selectFilterItem: (YourGameFilterItem) -> Unit,
    val items: List<YourGameListItem>,
    val refresh: () -> Unit,
) : ViewModelState

@Suppress("FunctionName")
fun YourGameListState_PreviewData(): YourGameListState =
    YourGameListState(
        filtersTitleText = "Filters",
        selectedFilterItem = YourGameFilterItem(YourGameFilter.All, YourGameFilter.All.name),
        filterItems = YourGameFilter.entries.map { YourGameFilterItem(it, it.name) },
        selectFilterItem = { },
        items = emptyList(),
        refresh = { },
    )
