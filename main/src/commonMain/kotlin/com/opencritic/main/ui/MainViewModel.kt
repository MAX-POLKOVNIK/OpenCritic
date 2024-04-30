package com.opencritic.main.ui

import com.opencritic.mvvm.BaseViewModel
import com.opencritic.resources.ImageResourceProvider
import com.opencritic.resources.StringResourceProvider
import com.opencritic.resources.get

class MainViewModel(
    stringResourceProvider: StringResourceProvider,
    imageResourceProvider: ImageResourceProvider,
) : BaseViewModel<MainState>() {
    override val initialState: MainState =
        MainState(
            tabs = listOf(
                Tab(
                    id = TabType.Main,
                    name = stringResourceProvider.tabMain.get(stringResourceProvider),
                    imageResource = imageResourceProvider.tabMain,
                ),
                Tab(
                    id = TabType.Search,
                    name = stringResourceProvider.tabSearch.get(stringResourceProvider),
                    imageResource = imageResourceProvider.tabSearch,
                ),
                Tab(
                    id = TabType.Browse,
                    name = stringResourceProvider.tabBrowse.get(stringResourceProvider),
                    imageResource = imageResourceProvider.tabBrowse,
                ),
                Tab(
                    id = TabType.Calendar,
                    name = stringResourceProvider.tabCalendar.get(stringResourceProvider),
                    imageResource = imageResourceProvider.tabCalendar,
                ),
                Tab(
                    id = TabType.YourLists,
                    name = stringResourceProvider.tabYourList.get(stringResourceProvider),
                    imageResource = imageResourceProvider.tabYourList,
                ),
            )
        )
}