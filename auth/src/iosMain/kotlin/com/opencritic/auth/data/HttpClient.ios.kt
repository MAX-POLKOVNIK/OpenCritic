package com.opencritic.auth.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin

internal actual fun HttpClient(): HttpClient =
    HttpClient(Darwin)