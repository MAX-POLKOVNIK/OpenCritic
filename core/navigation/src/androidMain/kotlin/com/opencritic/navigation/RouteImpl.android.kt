package com.opencritic.navigation

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

internal class RouteImpl<InitArgs : Any>(
    override val screenName: String,
    private val json: Json,
    private val serializer: KSerializer<Any?>,
) : Route<InitArgs> {
    override fun serialize(args: InitArgs): String =
        encodeBase64(json.encodeToString(serializer, args))

    @Suppress("UNCHECKED_CAST")
    override fun deserialize(string: String): InitArgs =
        json.decodeFromString(serializer, decodeBase64(string)) as InitArgs

    @OptIn(ExperimentalEncodingApi::class)
    private fun encodeBase64(string: String): String =
        Base64.Default.encode(string.encodeToByteArray())

    @OptIn(ExperimentalEncodingApi::class)
    private fun decodeBase64(string: String): String =
        Base64.Default.decode(string).decodeToString()
}