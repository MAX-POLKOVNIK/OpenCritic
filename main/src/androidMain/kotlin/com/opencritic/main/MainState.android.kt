package com.opencritic.main

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.opencritic.dashboard.DashboardScreen
import com.opencritic.game.browser.GameBrowserScreen
import com.opencritic.game.your.lists.YourGameListScreen
import com.opencritic.main.ui.MainContent
import com.opencritic.main.ui.TabType
import com.opencritic.news.ui.ArticleListScreen
import com.opencritic.resources.images.asPainter
import com.opencritic.resources.text.text
import com.opencritic.search.SearchScreen

@Composable
fun MainState(state: MainContent, navController: NavController) {
    Scaffold(
        contentWindowInsets = WindowInsets(top = 0),
        bottomBar = {
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
        },
        content = { innerPadding ->
            Crossfade(
                targetState = state.currentTab.id, label = "",
            ) { id ->
                when (id) {
                    TabType.Dashboard -> DashboardScreen(navController, modifier = Modifier.padding(innerPadding))
                    TabType.Search -> SearchScreen(navController, modifier = Modifier.padding(innerPadding))
                    TabType.Browse -> GameBrowserScreen(navController, modifier = Modifier.padding(innerPadding))
                    TabType.YourLists -> YourGameListScreen(navController, modifier = Modifier.padding(innerPadding))
                    TabType.News -> ArticleListScreen(navController, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    )
}