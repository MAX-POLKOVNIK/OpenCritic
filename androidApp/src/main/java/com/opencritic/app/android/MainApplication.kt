package com.opencritic.app.android

import android.app.Application
import com.opencritic.app.AndroidApp

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        AndroidApp.init(this)
    }
}