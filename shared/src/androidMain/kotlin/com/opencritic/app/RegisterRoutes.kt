package com.opencritic.app

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.opencritic.about.ui.aboutComposeRoute
import com.opencritic.auth.ui.authComposeRoute
import com.opencritic.calendar.ui.calendarComposeRoute
import com.opencritic.game.browser.periodGameBrowserComposeRoute
import com.opencritic.game.your.list.gameListRoute
import com.opencritic.games.details.gameDetailsComposeRoute
import com.opencritic.games.details.gameMediaComposeRoute
import com.opencritic.games.details.reviews.author.authorReviewsComposeRoute
import com.opencritic.games.details.reviews.game.gameReviewsComposeRoute
import com.opencritic.games.details.reviews.outlet.outletReviewsComposeRoute
import com.opencritic.main.MainScreen
import com.opencritic.navigation.MainDestination
import com.opencritic.news.ui.articleComposeRoute

fun registerRoutes(navController: NavController): NavGraphBuilder.() -> Unit = {
    composable(MainDestination.path) { MainScreen(navController) }
    gameDetailsComposeRoute(navController)
    gameMediaComposeRoute(navController)
    gameReviewsComposeRoute(navController)
    outletReviewsComposeRoute(navController)
    authorReviewsComposeRoute(navController)
    periodGameBrowserComposeRoute(navController)
    authComposeRoute(navController)
    gameListRoute(navController)
    articleComposeRoute(navController)
    calendarComposeRoute(navController)
    aboutComposeRoute(navController)
}