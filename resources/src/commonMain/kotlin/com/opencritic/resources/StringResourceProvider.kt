package com.opencritic.resources

interface StringResourceProvider {
    fun get(stringResource: StringResource, args: List<String>): String

    val gameActionWant: StringResource
    val gameActionPlayed: StringResource
    val gameActionFavorite: StringResource

    val openCriticRating: StringResource
    val topCriticAverage: StringResource
    val criticsRecommend: StringResource

    val mighty: StringResource
    val strong: StringResource
    val fair: StringResource
    val weak: StringResource

    val mightyDescription: StringResource
    val strongDescription: StringResource
    val fairDescription: StringResource
    val weakDescription: StringResource

    val whoIsMightyMan: StringResource
    val whoIsMightyManDescription: StringResource
    val whoIsMightyManColorDescription: StringResource

    val tabMain: StringResource
    val tabSearch: StringResource
    val tabBrowse: StringResource
    val tabCalendar: StringResource
    val tabYourList: StringResource

    val popularGames: StringResource
    val popularGamesDescription: StringResource

    val featuredDeals: StringResource
    val featuredDealsDescription: StringResource

    val recentlyReleased: StringResource
    val upcomingReleases: StringResource
    val reviewedToday: StringResource

    val viewMore: StringResource
    val reviewedThisWeek: StringResource

    val hallOfFameFormatted: StringResource
    val hallOfFameDescriptionFormatted: StringResource

    val buyNowOnFormatted: StringResource

    val viewAllReviewsFormatted: StringResource
}

fun StringResource.get(provider: StringResourceProvider, vararg args: String): String =
    provider.get(this, args.toList())