package com.opencritic.app

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import java.lang.ref.WeakReference

object AndroidApp : BaseApp() {
    private var contextRef: WeakReference<Context>? = WeakReference(null)

    fun init(context: Context) {
        contextRef = WeakReference(context)

        onInit(screenCreatorsModule)
    }

    override fun onKoinInit(koinApplication: KoinApplication) {
        koinApplication.androidContext(
            requireNotNull(contextRef?.get())
        )

        contextRef = null
    }
}