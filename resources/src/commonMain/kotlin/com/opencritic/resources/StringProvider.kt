package com.opencritic.resources

import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.ResourceFormatted
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.format

interface StringProvider {
    fun mapStringDescToString(stringDesc: StringDesc): String
}

fun StringProvider.getString(resourceId: StringResource): String =
    getStringDesc(resourceId)
        .let { mapStringDescToString(it) }

fun StringProvider.getFormattedString(resourceId: StringResource, vararg args: String): String =
    getFormattedString(resourceId, args.toList())

private fun StringProvider.getFormattedString(resourceId: StringResource, args: List<String>): String =
    getFormattedStringDesc(resourceId, args)
        .let { mapStringDescToString(it) }

private fun getStringDesc(resourceId: StringResource): StringDesc =
    StringDesc.Resource(resourceId)

private fun getFormattedStringDesc(resourceId: StringResource, args: List<String>): StringDesc =
    StringDesc.ResourceFormatted(resourceId, args)
