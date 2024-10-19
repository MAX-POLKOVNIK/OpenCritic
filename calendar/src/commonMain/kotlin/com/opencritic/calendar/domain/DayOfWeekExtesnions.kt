package com.opencritic.calendar.domain

import kotlinx.datetime.DayOfWeek

fun dayOfWeekOrdered(): List<DayOfWeek> =
    listOf(
        DayOfWeek.SUNDAY,
        DayOfWeek.MONDAY,
        DayOfWeek.TUESDAY,
        DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY,
        DayOfWeek.FRIDAY,
        DayOfWeek.SATURDAY,
    )

