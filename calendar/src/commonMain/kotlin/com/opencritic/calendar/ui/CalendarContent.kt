package com.opencritic.calendar.ui

import com.opencritic.mvvm.ScreenContent
import com.opencritic.resources.text.TextSource

data class CalendarContent(
    val description: TextSource,
    val cards: List<CalendarGameMonthCard>,
) : ScreenContent