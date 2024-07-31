package com.opencritic.resources.text

import kotlinx.datetime.toJavaLocalDate
import java.time.format.DateTimeFormatter

private val shortFormat = DateTimeFormatter.ofPattern("MMM dd")
private val format = DateTimeFormatter.ofPattern("MMM dd, yyyy")
private val fullFormat = DateTimeFormatter.ofPattern("MMMM dd, yyyy")

fun DateTextSource.text(): String =
    when (this.mode) {
        DateTextSource.Format.Short -> date.toJavaLocalDate().format(shortFormat)
        DateTextSource.Format.Medium -> date.toJavaLocalDate().format(format)
        DateTextSource.Format.Long -> date.toJavaLocalDate().format(fullFormat)
    }