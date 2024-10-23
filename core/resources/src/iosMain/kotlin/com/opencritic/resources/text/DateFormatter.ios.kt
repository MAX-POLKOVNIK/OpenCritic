package com.opencritic.resources.text

import kotlinx.datetime.LocalDate
import kotlinx.datetime.toNSDateComponents
import platform.Foundation.NSCalendar
import platform.Foundation.NSDateFormatter

actual fun LocalDate.formatDate(format: Format): String {
    val components = toNSDateComponents()
    val date = NSCalendar.currentCalendar.dateFromComponents(components)

    return if (date != null) {
        Formatter[format].stringFromDate(date)
    } else {
        ""
    }
}

private object Formatter {
    val map: Map<Format, NSDateFormatter> =
        Format.entries.associateWith { NSDateFormatter().apply { setDateFormat(it.pattern) } }

    operator fun get(format: Format): NSDateFormatter =
        requireNotNull(map[format])
}