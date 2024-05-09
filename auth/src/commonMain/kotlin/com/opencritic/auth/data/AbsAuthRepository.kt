package com.opencritic.auth.data

import com.opencritic.auth.domain.AuthRepository
import com.opencritic.auth.domain.AuthToken
import com.opencritic.logs.Logger
import io.ktor.client.request.request
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

abstract class AbsAuthRepository(
    private val logger: Logger,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AuthRepository {
    private val client by lazy { HttpClient() }

    override suspend fun authorizeByCallback(callbackUrl: String) =
        withContext(defaultDispatcher) {
            logger.log("START AUTH")
            val response = client.request(callbackUrl)

            logger.log("RESPONSE: $response")

            if (response.status.value !in 200..299)
                error("Auth error. Received $response on callback")

            val body = response.bodyAsText()

            logger.log("RESPONSE: $body")

            val startIndex = body.indexOf("{\"")
            val endIndex = body.indexOf("}'")

            if (startIndex == -1 || endIndex == -1) {
                error("Indexes invalid: start $startIndex end $endIndex")
            }

            val jsonString = body.substring(startIndex..endIndex)

            logger.log("JSON: $jsonString")

            val token: AuthToken = AuthJson.decodeFromString(jsonString)

            logger.log("TOKEN: $token")

            authToken = token
        }
}