package com.opencritic.resources.images

actual sealed interface IconResource {
    data class SfSymbol(val name: String) : IconResource
}

internal fun String.iconRes(): IconResource =
    IconResource.SfSymbol(this)