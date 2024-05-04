package com.opencritic.navigation

data class GameDetailsRoute(val gameId: Long) : Route("game/${gameId}")
data class GameMediaRoute(val gameId: Long) : Route("game/${gameId}/media")
data class UrlRoute(val url: String) : Route(url)
data class GameReviewsRoute(val gameId: Long) : Route("game/${gameId}/reviews")
data class OutletReviewsRoute(val outletId: Int) : Route("outlet/${outletId}")
data class AuthorReviewsRoute(val authorId: Int) : Route("author/${authorId}")
data class PeriodGameBrowserRoute(val period: Period) : Route("games/$period") {
    enum class Period {
        ReviewedThisWeek,
        RecentlyReleased,
        UpcomingReleases,
    }
}