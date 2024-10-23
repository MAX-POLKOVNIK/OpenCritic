package com.opencritic.logs

import android.util.Log

internal class UtilLogLogger : Logger {
    private val tag: String = "OpenCritic"

    override fun logDebug(message: String) {
        Log.d(tag, message)
    }

    override fun logInfo(message: String) {
        Log.i(tag, message)
    }

    override fun logWarn(message: String) {
        Log.w(tag, message)
    }

    override fun logError(message: String) {
        Log.e(tag, message)
    }
}