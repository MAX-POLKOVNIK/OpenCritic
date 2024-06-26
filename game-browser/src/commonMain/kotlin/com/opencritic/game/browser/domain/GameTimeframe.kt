package com.opencritic.game.browser.domain

import com.opencritic.resources.StringProvider
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
        GameTimeframe.AllTIme -> timeframeAllTime
        GameTimeframe.Upcoming -> timeframeUpcoming
        GameTimeframe.Last90Days -> timeframeLast90Days
        is GameTimeframe.Year -> timeframe.year.toString()
    }