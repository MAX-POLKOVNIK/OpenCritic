package com.opencritic.logs

import android.util.Log

internal class UtilLogLogger : Logger {
    override fun log(message: String) {
        Log.d("OpenCritic", message)
    }
}