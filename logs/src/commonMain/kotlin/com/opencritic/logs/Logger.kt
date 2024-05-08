package com.opencritic.logs

interface Logger {
    fun log(message: String)
}

fun Logger.log(any: Any?) {
    log(any.toString())
}

expect fun Logger(): Logger