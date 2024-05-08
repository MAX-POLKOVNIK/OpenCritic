package com.opencritic.main.ui

import com.opencritic.mvvm.BaseViewModel
import com.opencritic.resources.ImageResourceProvider
import com.opencritic.resources.StringProvider

class MainViewModel(
    stringProvider: StringProvider,
    imageResourceProvider: ImageResourceProvider,
) : BaseViewModel<MainState>() {
    override val initialState: MainState =
        MainState(
            tabs = listOf(
                Tab(
                    id = TabType.Main,
                    name = stringProvider.tabMain,
                    imageResource = imageResourceProvider.tabMain,
                ),
                Tab(
                    id = TabType.Search,
                    name = stringProvider.tabSearch,
                    imageResource = imageResourceProvider.tabSearch,
                ),
                Tab(
                    id = TabType.Browse,
                    name = stringProvider.tabBrowse,
                    imageResource = imageResourceProvider.tabBrowse,
                ),
                Tab(
                    id = TabType.YourLists,
                    name = stringProvider.tabYourList,
                    imageResource = imageResourceProvider.tabYourList,
                ),
            )
        )
}