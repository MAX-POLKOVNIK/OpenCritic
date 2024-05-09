package com.opencritic.auth.data

import com.opencritic.auth.domain.AuthToken
import com.opencritic.logs.Logger
import kotlinx.serialization.encodeToString
import platform.Foundation.NSUserDefaults

class AuthRepositoryImpl(logger: Logger) : AbsAuthRepository(logger) {
    private val userDefaults: NSUserDefaults by lazy { NSUserDefaults.standardUserDefaults }
    override var authToken: AuthToken?
        get() = userDefaults.stringForKey("authToken")
            ?.let { AuthJson.decodeFromString(it) }
        set(value) {
            value?.let { AuthJson.encodeToString<AuthToken>(value) }
                .let { userDefaults.setObject(it, forKey = "authToken") }
        }
}