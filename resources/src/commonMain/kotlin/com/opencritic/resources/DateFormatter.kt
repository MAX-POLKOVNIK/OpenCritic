package com.opencritic.resources

import kotlinx.datetime.LocalDate

interface DateFormatter {
    fun formatShort(date: LocalDate): String
}