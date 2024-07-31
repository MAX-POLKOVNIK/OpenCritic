package com.opencritic.resources.text

import dev.icerock.moko.resources.StringResource

data class ResourceTextSource(
    val resId: StringResource
) : TextSource

fun StringResource.asTextSource(): ResourceTextSource =
    ResourceTextSource(this)