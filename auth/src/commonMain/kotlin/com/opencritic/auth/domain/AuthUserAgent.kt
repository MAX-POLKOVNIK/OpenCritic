package com.opencritic.auth.domain

data object AuthUserAgent {
    override fun toString(): String =
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:124.0) Gecko/20100101 Firefox/124.0"
}