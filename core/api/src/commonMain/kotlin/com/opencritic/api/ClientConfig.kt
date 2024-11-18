package com.opencritic.api

import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json

fun <T : HttpClientEngineConfig> HttpClientConfig<T>.setup() {
    install(ContentNegotiation) { json(ApiJson) }
    install(Logging) {
        level = LogLevel.INFO
    }
}