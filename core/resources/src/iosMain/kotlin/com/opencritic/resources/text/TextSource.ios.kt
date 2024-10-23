package com.opencritic.resources.text

import dev.icerock.moko.resources.desc.ResourceStringDesc

@Suppress("unused")
fun TextSource.text(): String =
    when (this) {
        is StringTextSource -> string
        is ResourceFormattedTextSource -> desc.localized()
        is ResourceTextSource -> ResourceStringDesc(resId).localized()
    }