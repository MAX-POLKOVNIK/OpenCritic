package com.opencritic.main.ui

import com.opencritic.mvvm.BaseViewModel
import com.opencritic.resources.ImageResourceProvider
import com.opencritic.resources.StringProvider
import com.opencritic.resources.StringRes
import com.opencritic.resources.getString

class MainViewModel(
    private val stringProvider: StringProvider,
    private val imageResourceProvider: ImageResourceProvider,
) : BaseViewModel<MainState>() {
    override fun initialState(): MainState =
        MainState(
            tabs = listOf(
                Tab(
                    id = TabType.Main,
                    name = stringProvider.getString(StringRes.str_tab_main),
                    imageResource = imageResourceProvider.tabMain,
                ),
                Tab(
                    id = TabType.Search,
                    name = stringProvider.getString(StringRes.str_tab_search),
                    imageResource = imageResourceProvider.tabSearch,
                ),
                Tab(
                    id = TabType.Browse,
                    name = stringProvider.getString(StringRes.str_tab_browse),
                    imageResource = imageResourceProvider.tabBrowse,
                ),
                Tab(
                    id = TabType.YourLists,
                    name = stringProvider.getString(StringRes.str_tab_your_list),
                    imageResource = imageResourceProvider.tabYourList,
                ),
            )
        )
}