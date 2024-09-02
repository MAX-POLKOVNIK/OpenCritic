package com.opencritic.auth.domain

import com.opencritic.logs.Logger

class AuthRedirectInteractor(
    private val logger: Logger
) {
    operator fun invoke(url: String): Boolean {
        logger.log("REDIRECTED TO $url")

        if (url.startsWith("https://api.opencritic.com")) {
            logger.log("PREVENT REDIRECT TO $url")
            return false
        }

        return true
    }
}