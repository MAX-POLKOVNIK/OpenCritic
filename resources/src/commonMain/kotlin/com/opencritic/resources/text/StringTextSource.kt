package com.opencritic.resources.text

data class StringTextSource(
    val string: String,
) : TextSource

fun String.asTextSource(): StringTextSource =
    StringTextSource(this)