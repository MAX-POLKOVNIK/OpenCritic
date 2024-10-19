package com.opencritic.api.exceptions

import io.ktor.utils.io.errors.IOException

class NoInternetException(inner: IOException) : Exception(inner)