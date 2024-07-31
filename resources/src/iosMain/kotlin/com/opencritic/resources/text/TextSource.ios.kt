package com.opencritic.resources.text

fun TextSource.text(): String =
    when (this) {
        is DateTextSource -> text()
        is StringTextSource -> string
    }