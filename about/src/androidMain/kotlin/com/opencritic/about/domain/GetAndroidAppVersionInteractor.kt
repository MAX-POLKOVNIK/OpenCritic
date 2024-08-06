package com.opencritic.about.domain

import android.content.Context

class GetAndroidAppVersionInteractor(
    private val context: Context,
) : GetAppVersionInteractor {
    override fun invoke(): String =
        context.packageManager.getPackageInfo(context.packageName, 0).versionName
}