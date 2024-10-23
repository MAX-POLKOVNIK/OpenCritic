package com.opencritic.resources.text

import kotlinx.datetime.LocalDate

infix fun LocalDate.format(format: Format): TextSource =
    StringTextSource(formatDate(format))