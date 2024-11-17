package com.opencritic.calendar.ui

import com.opencritic.mvvm.ScreenContent
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toImmutableList

data class CalendarContent(
    val description: TextSource,
    val cards: ImmutableList<CalendarGameMonthCard>,
) : ScreenContent

@Suppress("FunctionName")
fun CalendarContent_PreviewData(): CalendarContent =
    CalendarContent(
        description = "See all of the top releases in gaming for the upcoming season. Games are evaluated for placement on the calendar on a case-by-case basis. If you think we're missing a game, email us at factcheck@opencritic.com.".asTextSource(),
        cards = buildList<CalendarGameMonthCard> {
            repeat(3) { i ->
                CalendarGameMonthCard_PreviewData(
                    nameText = "Month#$i",
                )
            }
        }.toImmutableList()
    )