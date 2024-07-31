package com.opencritic.resources.text

import dev.icerock.moko.resources.desc.desc

fun TextSource.text(): String =
    when (this) {
        is DateTextSource -> text()
        is StringTextSource -> string
        is ResourceFormattedTextSource -> desc.localized()
        is ResourceTextSource -> resId.desc().localized()
    }