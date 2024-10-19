package com.opencritic.search

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.opencritic.navigation.Route
import com.opencritic.navigation.ScreenCreator
import com.opencritic.search.api.ui.SearchRoute

data object SearchScreenCreator : ScreenCreator<SearchRoute.InitArgs>() {
    override val route: Route<SearchRoute.InitArgs> = SearchRoute

    @Composable
    override fun Composable(args: SearchRoute.InitArgs, navController: NavController) {
        SearchScreen(navController)
    }
}