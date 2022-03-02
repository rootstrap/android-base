package com.rootstrap.android

import android.app.Application
import com.rootstrap.android.di.initDI
import com.rootstrap.data.api.interceptors.WifiService
import com.rootstrap.data.metrics.Analytics
import com.rootstrap.data.metrics.GoogleAnalytics
import org.koin.core.annotation.KoinExperimentalAPI

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
