package com.opencritic.games.details.domain

import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource

enum class RatingTimeframe {
    AllTime,
    Today,
    Last7Days,
    ThisMonth,
    ThisYear,
}

fun RatingTimeframe.asTextSource(): TextSource =
    when (this) {
        RatingTimeframe.AllTime -> StringRes.str_rating_reviews_timeframe_all_time
        RatingTimeframe.Today -> StringRes.str_rating_reviews_timeframe_today
        RatingTimeframe.Last7Days -> StringRes.str_rating_reviews_timeframe_last_7_day
        RatingTimeframe.ThisMonth -> StringRes.str_rating_reviews_timeframe_this_month
        RatingTimeframe.ThisYear -> StringRes.str_rating_reviews_timeframe_this_year
    }.asTextSource()
