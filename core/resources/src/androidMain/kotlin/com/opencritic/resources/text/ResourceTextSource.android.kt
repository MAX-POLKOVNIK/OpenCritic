package com.opencritic.resources.text

import android.content.Context
import dev.icerock.moko.resources.desc.desc

fun ResourceTextSource.text(context: Context): String =
    resId.desc().toString(context)