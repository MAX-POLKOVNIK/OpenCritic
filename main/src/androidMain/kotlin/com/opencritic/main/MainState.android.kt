package com.opencritic.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.opencritic.auth.ui.authComposeRoute
import com.opencritic.dashboard.DashboardScreen
import com.opencritic.game.browser.GameBrowserScreen
import com.opencritic.game.browser.periodGameBrowserComposeRoute
import com.opencritic.game.your.list.gameListRoute
import com.opencritic.game.your.lists.YourGameListScreen
import com.opencritic.games.details.gameDetailsComposeRoute
import com.opencritic.games.details.gameMediaComposeRoute
import com.opencritic.games.details.reviews.author.authorReviewsComposeRoute
import com.opencritic.games.details.reviews.game.gameReviewsComposeRoute
import com.opencritic.games.details.reviews.outlet.outletReviewsComposeRoute
import com.opencritic.main.ui.MainState
import com.opencritic.main.ui.TabType
import com.opencritic.navigation.GameBrowserDestination
import com.opencritic.navigation.MainDestination
import com.opencritic.navigation.NewsDestination
import com.opencritic.navigation.SearchDestination
import com.opencritic.navigation.YourListDestination
import com.opencritic.news.ui.ArticleListScreen
import com.opencritic.news.ui.articleComposeRoute
import com.opencritic.resources.images.asPainter
import com.opencritic.resources.text.text
import com.opencritic.search.SearchScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainState(state: MainState, navController: NavHostController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    var selectedTab by remember { mutableStateOf(state.tabs.first()) }

    var showTopBar by rememberSaveable { mutableStateOf(true) }
    var showBottomBar by rememberSaveable { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    showTopBar = navBackStackEntry?.destination?.route in state.tabs.map { it.id.destination.path }
    showBottomBar = navBackStackEntry?.destination?.route in state.tabs.map { it.id.destination.path }

    Scaffold(
        contentWindowInsets = WindowInsets(top = 0),
        topBar = {
            if (showTopBar) {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp),
                    ),
                    title = {
                        Text(
                            text = selectedTab.name.text(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    scrollBehavior = scrollBehavior,
                )
            }
        },
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    val currentDestination = navBackStackEntry?.destination
                    state.tabs.forEach { tab ->
                        NavigationBarItem(
                            icon = { Icon(painter = tab.imageResource.asPainter(), contentDescription = null) },
                            label = { Text(tab.name.text()) },
                            selected = currentDestination?.hierarchy?.any { it.route == tab.id.destination.path } == true,
                            onClick = {
                                selectedTab = tab

                                val s = when (tab.id) {
                                    TabType.Main -> MainDestination
                                    TabType.Search -> SearchDestination
                                    TabType.Browse -> GameBrowserDestination
                                    TabType.YourLists -> YourListDestination
                                    TabType.News -> NewsDestination
                                }.path

                                navController.navigate(s) {
                                    // Pop up to the start destination of the graph to
                                    // avoid building up a large stack of destinations
                                    // on the back stack as users select items
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    // Avoid multiple copies of the same destination when
                                    // reselecting the same item
                                    launchSingleTop = true
                                    // Restore state when reselecting a previously selected item
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        },
        content = { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = MainDestination.path,
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(NewsDestination.path) { ArticleListScreen(navController) }
                composable(MainDestination.path) { DashboardScreen(navController) }
                composable(SearchDestination.path) { SearchScreen(navController) }
                composable(GameBrowserDestination.path) { GameBrowserScreen(navController) }
                composable(YourListDestination.path) { YourGameListScreen(navController) }
                gameDetailsComposeRoute(navController)
                gameMediaComposeRoute(navController)
                gameReviewsComposeRoute(navController)
                outletReviewsComposeRoute(navController)
                authorReviewsComposeRoute(navController)
                periodGameBrowserComposeRoute(navController)
                authComposeRoute(navController)
                gameListRoute(navController)
                articleComposeRoute(navController)
            }
        }
    )
}