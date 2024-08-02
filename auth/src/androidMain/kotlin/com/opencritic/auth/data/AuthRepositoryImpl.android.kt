package com.opencritic.auth.data

import android.content.Context
import android.content.SharedPreferences
import com.opencritic.auth.domain.AuthToken
import com.opencritic.logs.Logger
import kotlinx.serialization.encodeToString

class AuthRepositoryImpl(context: Context, logger: Logger) : AbsAuthRepositoryImpl(logger) {
    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences("auth", Context.MODE_PRIVATE)
    }

    override var authToken: AuthToken?
        get() = sharedPreferences.getString("authToken", null)
            ?.let { AuthJson.decodeFromString(it) }
        set(value) {
            value?.let { AuthJson.encodeToString<AuthToken>(value) }
                .let {
                    sharedPreferences.edit()
                        .putString("authToken", it)
                        .apply()
                }
        }

}
