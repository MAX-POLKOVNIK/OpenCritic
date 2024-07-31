package com.opencritic.game.browser.domain

import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

sealed interface GameTimeframe {
    data object AllTIme : GameTimeframe
    data object Upcoming : GameTimeframe
    data object Last90Days : GameTimeframe
    data class Year(val year: Int) : GameTimeframe

    companion object {
        val entries: List<GameTimeframe> =
            listOf(
                AllTIme,
                Upcoming,
                Last90Days,
                *(2013..(Clock.System.now().toLocalDateTime(TimeZone.UTC).year))
                    .reversed()
                    .map { Year(it) }
                    .toTypedArray()
            )
    }
}

fun GameTimeframe.asTextSource(): TextSource =
    when (this) {
        GameTimeframe.AllTIme -> StringRes.str_timeframe_all_time.asTextSource()
        GameTimeframe.Upcoming -> StringRes.str_timeframe_upcoming.asTextSource()
        GameTimeframe.Last90Days -> StringRes.str_timeframe_last_90_days.asTextSource()
        is GameTimeframe.Year -> year.toString().asTextSource()
    }