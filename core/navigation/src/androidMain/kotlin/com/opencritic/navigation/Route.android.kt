package com.opencritic.navigation

import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import kotlin.reflect.KType
import kotlin.reflect.typeOf

actual interface Route<InitArgs : Any> {
    actual val screenName: String

    fun registrationPath(): String =
        "${screenName}/{$argsKey}"

    fun navigationPath(args: InitArgs): String =
        "${screenName}/${serialize(args)}"

    fun serialize(args: InitArgs): String
    fun deserialize(string: String): InitArgs

    fun defaultArgs(): InitArgs = throw Exception("Default args not provided")
}

actual inline fun <reified InitArgs : Any> route(
    screenName: String,
): Route<InitArgs> =
    routeDefinition(
        screenName = screenName,
        argsType = typeOf<InitArgs>()
    )

fun <InitArgs : Any> routeDefinition(
    screenName: String,
    argsType: KType,
    json: Json = InitArgsJson
): Route<InitArgs> =
    RouteImpl(
        screenName = screenName,
        json = json,
        serializer = json.serializersModule.serializer(argsType)
    )

internal const val argsKey: String = "initArgs"