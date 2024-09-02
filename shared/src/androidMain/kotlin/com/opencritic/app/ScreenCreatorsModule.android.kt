package com.opencritic.app

import com.opencritic.about.ui.AboutScreenCreator
import com.opencritic.auth.ui.AuthScreenCreator
import com.opencritic.calendar.ui.CalendarScreenCreator
import com.opencritic.dashboard.DashboardScreenCreator
import com.opencritic.game.browser.GameBrowserScreenCreator
import com.opencritic.game.browser.PeriodGameBrowserScreenCreator
import com.opencritic.game.your.list.GameListScreenCreator
import com.opencritic.game.your.lists.GameListsScreenCreator
import com.opencritic.games.details.GameDetailsScreenCreator
import com.opencritic.games.details.reviews.author.AuthorReviewsScreenCreator
import com.opencritic.games.details.reviews.game.GameReviewsScreenCreator
import com.opencritic.games.details.reviews.outlet.OutletReviewsScreenCreator
import com.opencritic.halloffame.ui.HallsOfFameScreenCreator
import com.opencritic.navigation.ScreenCreators
import com.opencritic.news.ui.ArticleListScreenCreator
import com.opencritic.news.ui.ArticleScreenCreator
import com.opencritic.search.SearchScreenCreator
import org.koin.dsl.module

val screenCreatorsModule = module {
    single {
        ScreenCreators(
            ArticleListScreenCreator,
            DashboardScreenCreator,
            GameListsScreenCreator,
            GameBrowserScreenCreator,
            SearchScreenCreator,
            AboutScreenCreator,
            AuthScreenCreator,
            CalendarScreenCreator,
            PeriodGameBrowserScreenCreator,
            GameDetailsScreenCreator,
            AuthorReviewsScreenCreator,
            GameReviewsScreenCreator,
            OutletReviewsScreenCreator,
            GameListScreenCreator,
            HallsOfFameScreenCreator,
            ArticleScreenCreator,
        )
    }
}