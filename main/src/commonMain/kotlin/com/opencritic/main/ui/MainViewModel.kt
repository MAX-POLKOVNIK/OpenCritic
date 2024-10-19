package com.opencritic.main.ui

import com.opencritic.mvvm.BaseContentViewModel
import com.opencritic.mvvm.CommonViewModelState
import com.opencritic.resources.images.Icons
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.asTextSource

class MainViewModel : BaseContentViewModel<MainContent>() {
    override fun initialState(): CommonViewModelState<MainContent> =
        CommonViewModelState.content(
            content = listOf(
                Tab(
                    id = TabType.Dashboard,
                    name = StringRes.str_tab_main.asTextSource(),
                    imageResource = Icons.tabMain,
                ),
                Tab(
                    id = TabType.News,
                    name = StringRes.str_tab_news.asTextSource(),
                    imageResource = Icons.tabNews,
                ),
                Tab(
                    id = TabType.Search,
                    name = StringRes.str_tab_search.asTextSource(),
                    imageResource = Icons.tabSearch,
                ),
                Tab(
                    id = TabType.Browse,
                    name = StringRes.str_tab_browse.asTextSource(),
                    imageResource = Icons.tabBrowse,
                ),
                Tab(
                    id = TabType.YourLists,
                    name = StringRes.str_tab_your_list.asTextSource(),
                    imageResource = Icons.tabYourList,
                ),
            ).let {
                MainContent(
                    currentTab = it.first(),
                    tabs = it,
                    onTabSelected = ::onTabSelected
                )
            }
        )

    private fun onTabSelected(tab: Tab) {
        updateContentIfSet { copy(currentTab = tab) }
    }
}