package com.opencritic.resources.text

fun TextSource.text(): String =
    when (this) {
        is DateTextSource -> this.text()
        is StringTextSource -> string
    }
