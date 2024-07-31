package com.opencritic.resources.text

import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.format

data class ResourceFormattedTextSource(
    val desc: StringDesc,
) : TextSource

fun StringResource.asTextSource(vararg args: Any): ResourceFormattedTextSource =
    ResourceFormattedTextSource(format(args = args.toList()))