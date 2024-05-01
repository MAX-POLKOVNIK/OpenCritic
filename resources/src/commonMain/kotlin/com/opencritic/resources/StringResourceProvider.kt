package com.opencritic.resources

interface StringResourceProvider {
    fun get(stringResource: StringResource, args: List<String>): String

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

    val buyNowOnFormatted: StringResource
}

fun StringResource.get(provider: StringResourceProvider, vararg args: String): String =
    provider.get(this, args.toList())