package com.rootstrap.android

import android.app.Application
import com.rootstrap.android.di.myModule
import com.rootstrap.android.metrics.Analytics
import com.rootstrap.android.metrics.GoogleAnalytics
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(myModule)
        }
        Analytics.addProvider(GoogleAnalytics(this))
        // You need the api key in order to use MixPanel
        // Analytics.addProvider(MixPanelAnalytics(this))
    }
}
