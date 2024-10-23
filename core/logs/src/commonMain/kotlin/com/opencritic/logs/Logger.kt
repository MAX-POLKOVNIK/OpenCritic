package com.opencritic.logs

interface Logger {
    @Deprecated("Use log with level", ReplaceWith("logDebug(message)"))
    fun log(message: String) = logDebug(message)

    fun logDebug(message: String)
    fun logInfo(message: String)
    fun logWarn(message: String)
    fun logError(message: String)
}

@Deprecated("Use log with level", ReplaceWith("logDebug(message)"))
fun Logger.log(any: Any?) {
    log(any.toString())
}

expect fun Logger(): Logger