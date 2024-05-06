package com.opencritic.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json

internal actual fun HttpClient(): HttpClient =
    HttpClient(OkHttp) {
        install(ContentNegotiation) { json(ApiJson) }
        install(Logging) {
            level = LogLevel.INFO
        }
    }