package com.opencritic.main

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.opencritic.dashboard.DashboardScreen
import com.opencritic.main.ui.MainState
import com.opencritic.main.ui.TabType
import com.opencritic.navigation.GameBrowserDestination
import com.opencritic.navigation.MainDestination
import com.opencritic.navigation.SearchDestination
import com.opencritic.navigation.YourListDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainState(state: MainState, navController: NavHostController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    var selectedTab by remember { mutableStateOf(state.tabs.first()) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = selectedTab.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                scrollBehavior = scrollBehavior,
            )
        },
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                state.tabs.forEach { tab ->
                    NavigationBarItem(
                        icon = { Icon(painter = painterResource(tab.imageResource), contentDescription = null) },
                        label = { Text(tab.name) },
                        selected = currentDestination?.hierarchy?.any { it.route == tab.id.destination.path } == true,
                        onClick = {
                            selectedTab = tab

                            val s = when (tab.id) {
                                TabType.Main -> MainDestination
                                TabType.Search -> SearchDestination
                                TabType.Browse -> GameBrowserDestination
                                TabType.YourLists -> YourListDestination
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

        },
        content = { innerPadding ->
            NavHost(navController, startDestination = MainDestination.path, Modifier.padding(innerPadding)) {
                composable(MainDestination.path) { DashboardScreen(navController = navController) }
                composable(SearchDestination.path) { Text(text = "string3") }
                composable(GameBrowserDestination.path) { Text(text = "string4") }
                composable(YourListDestination.path) { Text(text = "string5") }
            }
        }
    )
}