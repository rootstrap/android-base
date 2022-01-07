package com.rootstrap.android

import android.app.Application
import com.rootstrap.android.di.initDI
import com.rootstrap.android.metrics.Analytics
import com.rootstrap.android.metrics.GoogleAnalytics
import com.rootstrap.data.api.interceptors.WifiService
import org.koin.core.KoinExperimentalAPI

@KoinExperimentalAPI
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
        setupServices()
        Analytics.addProvider(GoogleAnalytics(this))
        // You need the api key in order to use MixPanel
        // Analytics.addProvider(MixPanelAnalytics(this))
    }

    private fun setupServices() {
        WifiService.instance.initializeWithApplicationContext(applicationContext)
    }
}
