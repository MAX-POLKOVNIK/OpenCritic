package com.opencritic.main.ui

import com.opencritic.dashboard.api.DashboardRoute
import com.opencritic.game.browser.api.GameBrowserRoute
import com.opencritic.games.list.api.GameListsRoute
import com.opencritic.mvvm.BaseContentViewModel
import com.opencritic.mvvm.CommonViewModelState
import com.opencritic.navigation.ScreenCreators
import com.opencritic.news.api.ArticleListRoute
import com.opencritic.resources.images.Icons
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.asTextSource
import com.opencritic.search.api.ui.SearchRoute

class MainViewModel(
    private val screenCreators: ScreenCreators,
) : BaseContentViewModel<MainContent>() {
    override fun initialState(): CommonViewModelState<MainContent> =
        CommonViewModelState.content(
            content = listOf(
                Tab(
                    id = TabType.Dashboard,
                    name = StringRes.str_tab_main.asTextSource(),
                    imageResource = Icons.tabMain,
                    args = DashboardRoute.InitArgs,
                    screenCreator = screenCreators.find(DashboardRoute),
                ),
                Tab(
                    id = TabType.News,
                    name = StringRes.str_tab_news.asTextSource(),
                    imageResource = Icons.tabNews,
                    args = ArticleListRoute.InitArgs,
                    screenCreator = screenCreators.find(ArticleListRoute)
                ),
                Tab(
                    id = TabType.Search,
                    name = StringRes.str_tab_search.asTextSource(),
                    imageResource = Icons.tabSearch,
                    args = SearchRoute.InitArgs,
                    screenCreator = screenCreators.find(SearchRoute),
                ),
                Tab(
                    id = TabType.Browse,
                    name = StringRes.str_tab_browse.asTextSource(),
                    imageResource = Icons.tabBrowse,
                    args = GameBrowserRoute.InitArgs,
                    screenCreator = screenCreators.find(GameBrowserRoute),
                ),
                Tab(
                    id = TabType.YourLists,
                    name = StringRes.str_tab_your_list.asTextSource(),
                    imageResource = Icons.tabYourList,
                    args = GameListsRoute.InitArgs,
                    screenCreator = screenCreators.find(GameListsRoute),
                ),
            ).let {
                MainContent(
                    currentTab = it.first(),
                    tabs = it,
                    onTabSelected = ::onTabSelected
                )
            }
        )

    private fun onTabSelected(tab: Tab<*>) {
        updateContentIfSet { copy(currentTab = tab) }
    }
}