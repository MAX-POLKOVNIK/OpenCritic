package com.opencritic.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.DefaultJson
import io.ktor.serialization.kotlinx.json.json

internal actual fun HttpClient(): HttpClient =
    HttpClient(Darwin) {
        install(ContentNegotiation) { json(ApiJson) }
        install(Logging) {
            level = LogLevel.INFO
        }
    }