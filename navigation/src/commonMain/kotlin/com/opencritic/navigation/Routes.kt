package com.opencritic.navigation

private const val argName: String = "argName"

object GameDetailsDestination : Destination("game/{$argName}")

data class GameDetailsRoute(val gameId: Long) : Routed(GameDetailsDestination, argName, gameId)
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

data object MainDestination : Destination("main")
data object SearchDestination : Destination("search")
data object GameBrowserDestination : Destination("browser")
data object YourListDestination : Destination("your_lists")