package com.opencritic.app

import android.content.Context
import com.opencritic.resources.AndroidDateFormatter
import com.opencritic.resources.AndroidImageResourceProvider
import com.opencritic.resources.AndroidStringProvider
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import java.lang.ref.WeakReference

object AndroidApp : BaseApp() {
    private var contextRef: WeakReference<Context>? = WeakReference(null)

    fun init(context: Context) {
        contextRef = WeakReference(context)

        onInit(
            imageResourceProvider = AndroidImageResourceProvider(),
            stringProvider = AndroidStringProvider(context),
            dateFormatter = AndroidDateFormatter()
        )
    }

    override fun onKoinInit(koinApplication: KoinApplication) {
        koinApplication.androidContext(
            requireNotNull(contextRef?.get())
        )

        contextRef = null
    }
}