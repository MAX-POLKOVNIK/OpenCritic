package com.opencritic.api

import com.opencritic.logs.Logger
import io.ktor.client.plugins.logging.Logger as KLogger

internal class KtorLogger(private val logger: Logger) : KLogger {
    override fun log(message: String) {
        logger.logInfo(message)
    }
}