package com.opencritic.api

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp

internal actual val EngineFactory: HttpClientEngineFactory<HttpClientEngineConfig> = OkHttp