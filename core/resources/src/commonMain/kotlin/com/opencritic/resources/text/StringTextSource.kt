package com.opencritic.resources.text

import kotlin.jvm.JvmName

data class StringTextSource(
    val string: String,
) : TextSource

fun String.asTextSource(): StringTextSource =
    StringTextSource(this)

@JvmName("asTextSourceOrEmpty")
fun String?.asTextSource(): StringTextSource =
    StringTextSource(this ?: "")