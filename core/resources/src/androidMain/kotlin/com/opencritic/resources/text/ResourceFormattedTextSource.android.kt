package com.opencritic.resources.text

import android.content.Context

fun ResourceFormattedTextSource.text(context: Context): String =
    desc.toString(context)