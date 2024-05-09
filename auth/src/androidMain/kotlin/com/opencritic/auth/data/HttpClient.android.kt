package com.opencritic.auth.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp

internal actual fun HttpClient(): HttpClient =
    HttpClient(OkHttp)