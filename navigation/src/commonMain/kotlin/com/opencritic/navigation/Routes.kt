package com.opencritic.navigation

object GameDetailsDestination : Destination("game/{gameId}/{gameName}") {
    const val GAME_ID_KEY = "gameId"
    const val GAME_NAME_KEY = "gameName"
}

data class GameDetailsRoute(val gameId: Long, val gameName: String) : Routed(
    destination = GameDetailsDestination,
    args = mapOf(
        GameDetailsDestination.GAME_ID_KEY to gameId,
        GameDetailsDestination.GAME_NAME_KEY to gameName,
    )
)

object GameListDestination : Destination("list/{listId}/{listName}") {
    const val GAME_LIST_ID_KEY = "listId"
    const val GAME_LIST_NAME_KEY = "listName"
}

data class GameListRoute(val listId: String, val listName: String) : Routed(
    destination = GameListDestination,
    args = mapOf(
        GameListDestination.GAME_LIST_ID_KEY to listId,
        GameListDestination.GAME_LIST_NAME_KEY to listName,
    )
)

object GameMediaDestination : Destination("game/{gameId}/{gameName}/media") {
    const val GAME_ID_KEY = "gameId"
    const val GAME_NAME_KEY = "gameName"
}

data class GameMediaRoute(val gameId: Long, val gameName: String) : Routed(
    destination = GameMediaDestination,
    args = mapOf(
        GameMediaDestination.GAME_ID_KEY to gameId,
        GameMediaDestination.GAME_NAME_KEY to gameName,
    )
)

object GameReviewsDestination : Destination("game/{gameId}/{gameName}/reviews") {
    const val GAME_ID_KEY = "gameId"
    const val GAME_NAME_KEY = "gameName"
}

data class GameReviewsRoute(val gameId: Long, val gameName: String) : Routed(
    destination = GameReviewsDestination,
    args = mapOf(
        GameReviewsDestination.GAME_ID_KEY to gameId,
        GameReviewsDestination.GAME_NAME_KEY to gameName,
    )
)

object OutletReviewsDestination : Destination("outlet/{outletId}/{outletName}") {
    const val OUTLET_ID_KEY = "outletId"
    const val OUTLET_NAME_KEY = "outletName"
}

data class OutletReviewsRoute(val outletId: Int, val outletName: String) : Routed(
    destination = OutletReviewsDestination,
    args = mapOf(
        OutletReviewsDestination.OUTLET_ID_KEY to outletId,
        OutletReviewsDestination.OUTLET_NAME_KEY to outletName,
    )
)

object AuthorReviewsDestination : Destination("author/{authorId}/{authorName}") {
    const val AUTHOR_ID_KEY = "authorId"
    const val AUTHOR_NAME_KEY = "authorName"
}

data class AuthorReviewsRoute(val authorId: Int, val authorName: String) : Routed(
    destination = AuthorReviewsDestination,
    args = mapOf(
        AuthorReviewsDestination.AUTHOR_ID_KEY to authorId,
        AuthorReviewsDestination.AUTHOR_NAME_KEY to authorName,
    )
)

object ArticleDestination : Destination("article/{articleId}/{title}") {
    const val ARTICLE_ID_KEY = "articleId"
    const val ARTICLE_NAME_KEY = "title"
}

data class ArticleRoute(val articleId: Long, val title: String) : Routed(
    destination = ArticleDestination,
    args = mapOf(
        ArticleDestination.ARTICLE_ID_KEY to articleId,
        ArticleDestination.ARTICLE_NAME_KEY to title,
    )
)

object PeriodGameBrowserDestination : Destination("games/{period}") {
    const val PERIOD_KEY = "period"

    enum class Period {
        ReviewedThisWeek,
        RecentlyReleased,
        UpcomingReleases,
    }
}

data class PeriodGameBrowserRoute(val period: PeriodGameBrowserDestination.Period) : Routed(
    destination = PeriodGameBrowserDestination,
    args = mapOf(
        PeriodGameBrowserDestination.PERIOD_KEY to period.name
    )
)

data class UrlRoute(val url: String) : Route(url)

data class LinkShareRoute(val url: String) : Route(url)

data object MainDestination : Destination("Main")

data object AuthDestination : Destination("auth")

data object AuthRoute : Routed(
    destination = AuthDestination,
    args = emptyMap()
)

data object CalendarDestination : Destination("calendar")
data object CalendarRoute : Routed(
    destination = CalendarDestination,
    args = emptyMap()
)