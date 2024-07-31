package com.opencritic.resources

actual sealed interface IconResource {
    data class SfSymbol(val name: String) : IconResource
}

internal fun String.iconRes(): IconResource =
    IconResource.SfSymbol(this)