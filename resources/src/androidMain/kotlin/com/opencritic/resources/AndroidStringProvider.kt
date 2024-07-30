package com.opencritic.resources

import android.content.Context
import dev.icerock.moko.resources.desc.StringDesc

class AndroidStringProvider(
    private val context: Context
) : StringProvider {
    override fun mapStringDescToString(stringDesc: StringDesc): String =
        stringDesc.toString(context)
}
