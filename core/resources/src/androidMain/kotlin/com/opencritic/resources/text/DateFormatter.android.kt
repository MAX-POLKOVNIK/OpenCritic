package com.opencritic.resources.text

import kotlinx.datetime.LocalDate
import kotlinx.datetime.toJavaLocalDate
import java.time.format.DateTimeFormatter

actual fun LocalDate.formatDate(format: Format): String =
    toJavaLocalDate()
        .format(Formatter[format])

private object Formatter {
    val map: Map<Format, DateTimeFormatter> =
        Format.entries.associateWith { DateTimeFormatter.ofPattern(it.pattern) }

    operator fun get(format: Format): DateTimeFormatter =
        requireNotNull(map[format])
}