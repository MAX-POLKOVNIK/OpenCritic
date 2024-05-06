package com.opencritic.resources

import android.content.Context

class AndroidStringProvider(
    private val context: Context,
) : StringProvider {
    private fun string(stringResource: Int, args: List<String> = emptyList()): String =
        if (args.isEmpty()) context.getString(stringResource)
        else context.getString(stringResource, *args.toTypedArray())

    override val gameActionFavorite: String get() = string(R.string.str_game_action_favorite)
    override val gameActionPlayed: String get() = string(R.string.str_game_action_played)
    override val gameActionWant: String get() = string(R.string.str_game_action_want)

    override val criticsRecommend: String get() = string(R.string.str_critics_recommend)
    override val openCriticRating: String get() = string(R.string.str_open_critic_rating)
    override val topCriticAverage: String get() = string(R.string.str_top_critic_average)

    override val fair: String get() = string(R.string.str_fair)
    override val fairDescription: String get() = string(R.string.str_fair_description)

    override val mighty: String get() = string(R.string.str_mighty)
    override val mightyDescription: String get() = string(R.string.str_mighty_description)

    override val strong: String get() = string(R.string.str_strong)
    override val strongDescription: String get() = string(R.string.str_strong_description)

    override val weak: String get() = string(R.string.str_weak)
    override val weakDescription: String get() = string(R.string.str_weak_description)

    override val whoIsMightyMan: String get() = string(R.string.who_is_mighty_man)
    override val whoIsMightyManColorDescription: String get() = string(R.string.who_is_mighty_man_color_description)
    override val whoIsMightyManDescription: String get() = string(R.string.who_is_mighty_man_description)

    override fun buyNowOnFormatted(name: String): String = string(R.string.str_buy_now_on, args = listOf(name))

    override val popularGames: String get() = string(R.string.str_popular_games)
    override val popularGamesDescription: String get() = string(R.string.str_popular_games_description)

    override val featuredDeals: String get() = string(R.string.str_featured_deals)
    override val featuredDealsDescription: String get() = string(R.string.str_featured_deals_description)

    override val recentlyReleased: String get() = string(R.string.str_recently_released)
    override val reviewedToday: String get() = string(R.string.str_reviewed_today)
    override val upcomingReleases: String get() = string(R.string.str_upcoming_releases)

    override val viewMore: String get() = string(R.string.str_view_more)
    override val reviewedThisWeek: String get() = string(R.string.str_reviewed_this_week)

    override fun hallOfFameFormatted(year: String): String = string(R.string.str_hall_of_fame, args = listOf(year))
    override fun hallOfFameDescriptionFormatted(year: String): String = string(R.string.str_hall_of_fame_description, args = listOf(year))

    override val tabBrowse: String get() = string(R.string.str_tab_browse)
    override val tabCalendar: String get() = string(R.string.str_tab_calendar)
    override val tabMain: String get() = string(R.string.str_tab_main)
    override val tabSearch: String get() = string(R.string.str_tab_search)
    override val tabYourList: String get() = string(R.string.str_tab_your_list)

    override val viewAll: String get() = string(R.string.str_view_all)
    override fun viewAllReviewsFormatted(count: String): String = string(R.string.str_view_all_reviews, args = listOf(count))
    override fun criticReviewsForFormatted(name: String): String = string(R.string.str_critic_reviews_for_formatted, args = listOf(name))
    override val readFullReview: String get() = string(R.string.str_read_full_review)

    override val media: String get() = string(R.string.str_media)
    override val screenshots: String get() = string(R.string.str_screenshots)
    override val trailers: String get() = string(R.string.str_trailers)

    override val critic: String get() = string(R.string.str_critic)
    override val game: String get() = string(R.string.str_game)
    override val outlet: String get() = string(R.string.str_outlet)
    override val searchHint: String get() = string(R.string.str_search_hint)

    override fun gameScreenshotsAndTrailers(name: String): String = string(R.string.str_game_screenshots_and_trailers, args = listOf(name))

    override fun gameReviewRankedDescription(name: String, percent: String): String = string(R.string.str_game_review_ranked_description, args = listOf(name, percent))

    override val sort: String get() = string(R.string.str_sort)
    override val sortDefault: String get() = string(R.string.str_sort_default)
    override val sortMostPopular: String get() = string(R.string.str_sort_most_popular)
    override val sortNewestFirst: String get() = string(R.string.str_sort_newest)
    override val sortOldestFirst: String get() = string(R.string.str_sort_oldest)
    override val sortScoreHighestToLowest: String get() = string(R.string.str_sort_highest)
    override val sortScoreLowestToHighest: String get() = string(R.string.str_sort_lowest)

    override fun averageScoreFormatted(number: String): String = string(R.string.str_average_score_formatted, args = listOf(number))
    override fun gamesRecommendedFormatted(number: String): String = string(R.string.str_games_recommended_formatted, args = listOf(number))
    override fun gamesReviewedFormatted(number: String): String = string(R.string.str_games_reviewed_formatted, args = listOf(number))
    override fun medianScoreFormatted(number: String): String = string(R.string.str_median_score_formatted, args = listOf(number))

    override fun reviewsOf(name: String): String = string(R.string.str_reviews_of, args = listOf(name))
    override val reviews: String get() = string(R.string.str_reviews)

    override val homepage: String get() = string(R.string.str_home_page)

    override fun authorIsNotClaimedFormatted(name: String): String = string(R.string.str_author_is_not_claimed, args = listOf(name))

    override val favoriteGames: String get() = string(R.string.str_favorite_games)

    override val allPlatforms: String get() = string(R.string.str_all_platforms)
    override val platform: String get() = string(R.string.str_platform)

    override val sortAtoZ: String get() = string(R.string.str_sort_a_to_z)
    override val sortPercentRecommended: String get() = string(R.string.str_sort_percent_recommended)
    override val sortReleaseDate: String get() = string(R.string.str_sort_release_date)
    override val sortReviewsCount: String get() = string(R.string.str_sort_review_count)
    override val sortScore: String get() = string(R.string.str_sort_score)

    override val timeframe: String get() = string(R.string.str_timeframe)
    override val timeframeAllTime: String get() = string(R.string.str_timeframe_all_time)
    override val timeframeLast90Days: String get() = string(R.string.str_timeframe_last_90_days)
    override val timeframeUpcoming: String get() = string(R.string.str_timeframe_upcoming)
}