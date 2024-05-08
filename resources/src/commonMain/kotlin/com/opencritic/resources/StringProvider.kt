package com.opencritic.resources

interface StringProvider {
    val gameActionWant: String
    val gameActionPlayed: String
    val gameActionFavorite: String

    val openCriticRating: String
    val topCriticAverage: String
    val criticsRecommend: String

    val mighty: String
    val strong: String
    val fair: String
    val weak: String

    val mightyDescription: String
    val strongDescription: String
    val fairDescription: String
    val weakDescription: String

    val whoIsMightyMan: String
    val whoIsMightyManDescription: String
    val whoIsMightyManColorDescription: String

    val tabMain: String
    val tabSearch: String
    val tabBrowse: String
    val tabCalendar: String
    val tabYourList: String

    val popularGames: String
    val popularGamesDescription: String

    val featuredDeals: String
    val featuredDealsDescription: String

    val recentlyReleased: String
    val upcomingReleases: String
    val reviewedToday: String

    val viewMore: String
    val reviewedThisWeek: String

    fun hallOfFameFormatted(year: String): String
    fun hallOfFameDescriptionFormatted(year: String): String

    fun buyNowOnFormatted(name: String): String

    val viewAll: String
    fun viewAllReviewsFormatted(count: String): String
    fun criticReviewsForFormatted(name: String): String
    val readFullReview: String

    val media: String
    val screenshots: String
    val trailers: String

    val searchHint: String

    val game: String
    val critic: String
    val outlet: String

    fun gameReviewRankedDescription(name: String, percent: String): String

    val sort: String
    val sortDefault: String
    val sortMostPopular: String
    val sortScoreHighestToLowest: String
    val sortScoreLowestToHighest: String
    val sortNewestFirst: String
    val sortOldestFirst: String

    val sortReleaseDate: String
    val sortScore: String
    val sortReviewsCount: String
    val sortPercentRecommended: String
    val sortAtoZ: String

    val timeframeUpcoming: String
    val timeframeAllTime: String
    val timeframeLast90Days: String

    fun gameScreenshotsAndTrailers(name: String): String

    val reviews: String
    fun reviewsOf(name: String): String

    val homepage: String

    fun gamesReviewedFormatted(number: String): String
    fun averageScoreFormatted(number: String): String
    fun medianScoreFormatted(number: String): String
    fun gamesRecommendedFormatted(number: String): String

    fun authorIsNotClaimedFormatted(name: String): String

    val favoriteGames: String

    val platform: String
    val allPlatforms: String
    val timeframe: String
}