package com.opencritic.resources.text

import kotlinx.datetime.LocalDate

enum class Format(val pattern: String) {
    Short("MMM dd"),
    Medium("MMM dd, yyyy"),
    Long("MMMM dd, yyyy"),
}

expect fun LocalDate.formatDate(format: Format): String