package com.opencritic.game.browser.domain

import com.opencritic.resources.StringProvider
import com.opencritic.resources.StringRes
import com.opencritic.resources.getString
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

fun StringProvider.timeframeNameOf(timeframe: GameTimeframe): String =
    when (timeframe) {
        GameTimeframe.AllTIme -> getString(StringRes.str_timeframe_all_time)
        GameTimeframe.Upcoming -> getString(StringRes.str_timeframe_upcoming)
        GameTimeframe.Last90Days -> getString(StringRes.str_timeframe_last_90_days)
        is GameTimeframe.Year -> timeframe.year.toString()
    }