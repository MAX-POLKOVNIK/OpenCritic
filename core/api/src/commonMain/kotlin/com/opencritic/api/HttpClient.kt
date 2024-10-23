package com.opencritic.api

import com.opencritic.logs.Logger
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json

internal expect val EngineFactory: HttpClientEngineFactory<HttpClientEngineConfig>

internal fun HttpClient(log: Logger): HttpClient =
    HttpClient(EngineFactory) {
        install(ContentNegotiation) { json(ApiJson) }
        install(Logging) {
            logger = KtorLogger(log)
            level = LogLevel.INFO
        }
    }

