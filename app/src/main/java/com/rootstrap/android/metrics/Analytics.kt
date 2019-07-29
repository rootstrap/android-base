package com.rootstrap.android.metrics

import android.annotation.SuppressLint
import com.rootstrap.android.metrics.base.BaseAnalytics
import com.rootstrap.android.metrics.base.Provider
import com.rootstrap.android.metrics.base.TrackEvent

val appAnalytics: BaseAnalytics by lazy {
    Analytics.instance
}

class Analytics(var providers: ArrayList<Provider>? = ArrayList()) : BaseAnalytics {

    companion object {
        fun init(providers: ArrayList<Provider>? = ArrayList()) {
            instance = Analytics(providers)
        }

        @SuppressLint("StaticFieldLeak")
        lateinit var instance: BaseAnalytics
            private set
    }

    override fun addProvider(provider: Provider) {
        providers!!.add(provider)
    }

    override fun logOut() {
        providers!!.forEach { it.reset() }
    }

    override fun logIn() {
        providers!!.forEach { it.init() }
    }

    override fun signUp() {
        providers!!.forEach { it.init() }
    }

    override fun visitPage(pageName: String, data: Any) {
        providers!!.forEach { it.track(TrackEvent(pageName, data))}
    }
}
