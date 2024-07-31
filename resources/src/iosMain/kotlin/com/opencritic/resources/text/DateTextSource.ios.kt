package com.opencritic.resources.text

import kotlinx.datetime.LocalDate
import kotlinx.datetime.toNSDateComponents
import platform.Foundation.NSCalendar
import platform.Foundation.NSDateFormatter

private val shortFormat = "MMM dd"
private val meduimFormat = "MMM dd, yyyy"
private val longFormat = "MMMM dd, yyyy"

fun DateTextSource.text(): String =
    when (mode) {
        DateTextSource.Format.Short -> date.format(shortFormat)
        DateTextSource.Format.Medium -> date.format(meduimFormat)
        DateTextSource.Format.Long -> date.format(longFormat)
    }

private fun LocalDate.format(format: String): String {
    val formatter = NSDateFormatter()

    formatter.setDateFormat(format)

    val components = toNSDateComponents()
    val date = NSCalendar.currentCalendar.dateFromComponents(components)

    return if (date != null) {
        formatter.stringFromDate(date)
    } else {
        ""
    }
}
