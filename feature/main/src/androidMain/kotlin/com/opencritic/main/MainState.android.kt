package com.opencritic.main

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.opencritic.dashboard.DashboardScreen
import com.opencritic.game.browser.GameBrowserScreen
import com.opencritic.game.your.lists.YourGameListScreen
import com.opencritic.main.ui.MainContent
import com.opencritic.main.ui.Tab
import com.opencritic.main.ui.TabType
import com.opencritic.news.ui.ArticleListScreen
import com.opencritic.resources.images.asPainter
import com.opencritic.resources.text.text
import com.opencritic.search.SearchScreen

@Composable
fun MainState(
    state: MainContent,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Crossfade(
            targetState = state.currentTab,
            label = "",
            modifier = Modifier
                .weight(1f)
        ) { tab ->
            TabContent(tab, navController)
        }

        NavigationBar {
            state.tabs.forEach { tab ->
                NavigationBarItem(
                    icon = { Icon(painter = tab.imageResource.asPainter(), contentDescription = null) },
                    label = { Text(tab.name.text()) },
                    selected = state.currentTab == tab,
                    onClick = { state.onTabSelected(tab) }
                )
            }
        }
    }
}

@Composable
fun <InitArgs : Any> TabContent(tab: Tab<InitArgs>, navController: NavController) {
    requireNotNull(tab.screenCreator).Composable(requireNotNull(tab.args), navController)
}