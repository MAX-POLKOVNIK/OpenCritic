package com.opencritic.mvvm

import com.opencritic.api.exceptions.NoInternetException
import com.opencritic.api.exceptions.UnsuccessfulResponseException
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource

fun Throwable.asTextSource(): TextSource =
    when (this) {
        is UnsuccessfulResponseException -> StringRes.str_error_server_error.asTextSource()
        is NoInternetException -> StringRes.str_error_no_internet.asTextSource()
        else -> StringRes.str_error_unknown.asTextSource()
    }
