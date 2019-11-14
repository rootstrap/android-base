package com.rootstrap.android

import android.app.Application
import com.rootstrap.android.metrics.Analytics
import com.rootstrap.android.metrics.GoogleAnalytics
import com.rootstrap.android.metrics.annotations.processVisits
import com.rootstrap.android.metrics.appAnalytics
import com.rootstrap.android.ui.activity.main.MainActivity

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Analytics.init()

        appAnalytics.addProvider(GoogleAnalytics(this))
        processVisits(MainActivity::class)
        //You need the api key in order to use MixPanel
        // appAnalytics.addProvider(MixPanelAnalytics(applicationContext))
    }
}
