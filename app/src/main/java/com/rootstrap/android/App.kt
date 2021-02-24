package com.rootstrap.android

import android.app.Application
import com.rootstrap.android.metrics.Analytics
import com.rootstrap.android.metrics.GoogleAnalytics
import com.rootstrap.android.util.Prefs
import com.squareup.otto.Bus
import dagger.hilt.android.HiltAndroidApp

val prefs: Prefs by lazy {
    App.prefs!!
}

val bus: Bus by lazy {
    App.bus!!
}

@HiltAndroidApp
class App : Application() {

    companion object {
        var prefs: Prefs? = null
        var bus: Bus? = null
    }

    override fun onCreate() {
        super.onCreate()

        prefs = Prefs(applicationContext)
        bus = Bus()

        Analytics.addProvider(GoogleAnalytics(this))
        // You need the api key in order to use MixPanel
        // Analytics.addProvider(MixPanelAnalytics(this))
    }
}
