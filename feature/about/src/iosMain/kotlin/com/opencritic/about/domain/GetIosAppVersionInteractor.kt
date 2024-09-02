package com.opencritic.about.domain

import platform.Foundation.NSBundle

class GetIosAppVersionInteractor : GetAppVersionInteractor {
    override fun invoke(): String =
        NSBundle.mainBundle.infoDictionary()?.get("CFBundleShortVersionString") as String
}