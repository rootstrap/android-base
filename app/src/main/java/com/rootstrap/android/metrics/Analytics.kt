package com.rootstrap.android.metrics

import android.annotation.SuppressLint
import com.rootstrap.android.metrics.base.BaseAnalytics
import com.rootstrap.android.metrics.base.Provider
import com.rootstrap.android.metrics.base.TrackEvent
import com.rootstrap.android.metrics.base.UserProperty

val appAnalytics: BaseAnalytics by lazy {
    Analytics.instance
}

class Analytics(var providers: ArrayList<Provider> = ArrayList()) : BaseAnalytics {

    companion object {
        fun init(providers: ArrayList<Provider> = ArrayList()) {
            instance = Analytics(providers)
        }

        @SuppressLint("StaticFieldLeak")
        lateinit var instance: BaseAnalytics
            private set
    }

    override fun addProvider(provider: Provider) {
        provider.let {
            providers.add(it)
            it.init()
        }
    }

    override fun addOrEditProperty(property: UserProperty) {
        providers.forEach { it.addOrEditUserSuperProperty(property) }
    }

    override fun addOrEditProperties(properties: List<UserProperty>) {
        providers.forEach {
            properties.forEach { property ->
                it.addOrEditUserSuperProperty(property)
            }
        }
    }

    override fun init() {
        providers.forEach { it.init() }
    }

    override fun track(event: TrackEvent) {
        providers.forEach { it.track(event) }
    }
}
