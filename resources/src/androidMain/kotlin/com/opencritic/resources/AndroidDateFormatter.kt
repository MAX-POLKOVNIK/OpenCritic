package com.opencritic.resources

import kotlinx.datetime.LocalDate
import kotlinx.datetime.toJavaLocalDate
import java.time.format.DateTimeFormatter

class AndroidDateFormatter : DateFormatter {
    private val shortFormat = DateTimeFormatter.ofPattern("MMM dd")
    private val format = DateTimeFormatter.ofPattern("MMM dd, yyyy")
    private val fullFormat = DateTimeFormatter.ofPattern("MMMM dd, yyyy")

    override fun formatShort(date: LocalDate): String =
        date.toJavaLocalDate().format(shortFormat)

    override fun format(date: LocalDate): String =
        date.toJavaLocalDate().format(format)

    override fun formatFull(date: LocalDate): String =
        date.toJavaLocalDate().format(fullFormat)
}