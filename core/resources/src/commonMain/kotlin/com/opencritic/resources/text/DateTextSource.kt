package com.opencritic.resources.text

import kotlinx.datetime.LocalDate

data class DateTextSource(
    val date: LocalDate,
    val mode: Format,
) : TextSource {
    enum class Format {
        Short,
        Medium,
        Long,
    }
}

infix fun LocalDate.format(format: DateTextSource.Format): DateTextSource =
    DateTextSource(this, format)