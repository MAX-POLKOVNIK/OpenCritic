package com.opencritic.logs

import platform.Foundation.NSLog

internal class NSLogLogger : Logger {
    override fun log(message: String) {
        NSLog(message)
    }
}