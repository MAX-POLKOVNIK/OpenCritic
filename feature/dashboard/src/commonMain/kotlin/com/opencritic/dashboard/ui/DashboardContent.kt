package com.opencritic.dashboard.ui

import com.opencritic.dashboard.domain.GameDeal
import com.opencritic.dashboard.domain.PosterGame
import com.opencritic.games.GameRank
import com.opencritic.games.Tier
import com.opencritic.mvvm.ScreenContent
import com.opencritic.resources.text.asTextSource

data class DashboardContent(
    val popularGamesTitle: DashboardTitleListItem,
    val popularGames: DashboardPosterGamesHorizontalListItem,
    val dealsTitle: DashboardTitleListItem,
    val deals: DashboardDealsHorizontalListItem,
    val recentlyReleased: DashboardSublistListItem,
    val upcomingReleases: DashboardSublistListItem,
    val reviewedToday: DashboardSublistListItem,
    val hallOfFameTitle: DashboardTitleListItem,
    val hallOfFame: DashboardPosterGamesHorizontalListItem,
    val whoIsMightyMan: DashboardMightyManListItem,
    val switchTitle: DashboardTitleListItem,
    val switchGames: DashboardPosterGamesHorizontalListItem,
    val xboxTitle: DashboardTitleListItem,
    val xboxGames: DashboardPosterGamesHorizontalListItem,
    val playstationTitle: DashboardTitleListItem,
    val playstationGames: DashboardPosterGamesHorizontalListItem,
) : ScreenContent

@Suppress("FunctionName")
fun DashboardContent_PreviewData(): DashboardContent =
    DashboardContent(
        popularGamesTitle = DashboardTitleListItem(
            title = "Hello".asTextSource(),
            subtitle = "Second string".asTextSource()
        ),
        popularGames = DashboardPosterGamesHorizontalListItem(
            popularGames = listOf(
                PosterGame(
                    id = 1,
                    name = "Test some long",
                    posterUrl = "https://img.opencritic.com/game/14353/o/cDwMyHmW.jpg",
                    rank = GameRank(tier = Tier.Fair, score = 32)
                ),
                PosterGame(
                    id = 2,
                    name = "Test",
                    posterUrl = "https://img.opencritic.com/game/14353/o/cDwMyHmW.jpg",
                    rank = GameRank(tier = Tier.Fair, score = 32)
                ),
                PosterGame(
                    id = 3,
                    name = "Test",
                    posterUrl = "https://img.opencritic.com/game/14353/o/cDwMyHmW.jpg",
                    rank = GameRank(tier = Tier.Fair, score = 32)
                )
            ),
            onClick = {}
        ),
        dealsTitle = DashboardTitleListItem(
            title = "Deals".asTextSource(),
            subtitle = "Deals description".asTextSource()
        ),
        deals = DashboardDealsHorizontalListItem(
            deals = listOf(
                GameDeal(
                    game = PosterGame(
                        id = 1,
                        name = "Test some long name",
                        posterUrl = "https://img.opencritic.com/game/14353/o/cDwMyHmW.jpg",
                        rank = GameRank(tier = Tier.Fair, score = 32)
                        ),
                    name = "Amazon",
                    price = 49.99f,
                    externalUrl = ""
                ),
                GameDeal(
                    game = PosterGame(
                        id = 2,
                        name = "Test some long name",
                        posterUrl = "https://img.opencritic.com/game/14353/o/cDwMyHmW.jpg",
                        rank = GameRank(tier = Tier.Fair, score = 32)
                    ),
                    name = "Amazon",
                    price = 49.99f,
                    externalUrl = ""
                ),
                GameDeal(
                    game = PosterGame(
                        id = 3,
                        name =  "Test some long name",
                        posterUrl = "https://img.opencritic.com/game/14353/o/cDwMyHmW.jpg",
                        rank = GameRank(tier = Tier.Fair, score = 32)
                    ),
                    name = "Amazon",
                    price = 49.99f,
                    externalUrl = ""
                ),
            ),
            onClick = { },
            onBuyNowClick = {}
        ),
        recentlyReleased = DashboardSublistListItem(
            id = DashboardSublistListItem.Type.RecentlyReleased,
            titleText = "".asTextSource(),
            items = emptyList(),
            viewMoreText = "".asTextSource(),
            onMoreClick = {}
        ),
        upcomingReleases = DashboardSublistListItem(
            id = DashboardSublistListItem.Type.RecentlyReleased,
            titleText = "".asTextSource(),
            items = emptyList(),
            viewMoreText = "".asTextSource(),
            onMoreClick = {}
        ),
        reviewedToday = DashboardSublistListItem(
            id = DashboardSublistListItem.Type.RecentlyReleased,
            titleText = "".asTextSource(),
            items = emptyList(),
            viewMoreText = "".asTextSource(),
            onMoreClick = {}
        ),
        hallOfFameTitle = DashboardTitleListItem(
            title = "Hall of fame".asTextSource(),
            subtitle = "Hall of fame description".asTextSource(),
            buttonTitle = "View all".asTextSource(),
        ),
        hallOfFame = DashboardPosterGamesHorizontalListItem(
            popularGames = emptyList(),
            onClick = {}
        ),
        whoIsMightyMan = DashboardMightyManListItem(),
        switchTitle = DashboardTitleListItem(
            title = "Hall of fame".asTextSource(),
            subtitle = "Hall of fame description".asTextSource()
        ),
        switchGames = DashboardPosterGamesHorizontalListItem(
            popularGames = emptyList(),
            onClick = {}
        ),
        xboxTitle = DashboardTitleListItem(
            title = "Hall of fame".asTextSource(),
            subtitle = "Hall of fame description".asTextSource()
        ),
        xboxGames = DashboardPosterGamesHorizontalListItem(
            popularGames = emptyList(),
            onClick = {}
        ),
        playstationTitle = DashboardTitleListItem(
            title = "Hall of fame".asTextSource(),
            subtitle = "Hall of fame description".asTextSource()
        ),
        playstationGames = DashboardPosterGamesHorizontalListItem(
            popularGames = emptyList(),
            onClick = {},
        )
)