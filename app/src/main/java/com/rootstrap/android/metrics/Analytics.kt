package com.rootstrap.android.metrics

import com.rootstrap.android.metrics.base.BaseAnalytics
import com.rootstrap.android.metrics.base.Provider
import com.rootstrap.android.metrics.base.TrackEvent
import com.rootstrap.android.metrics.base.UserProperty

object Analytics : BaseAnalytics {
    var providers: ArrayList<Provider> = ArrayList()

    override fun addProviders(providers: ArrayList<Provider>) {
        this.providers = providers
    }

    override fun addProvider(provider: Provider) {
        provider.let {
            providers.add(it)
            it.indentifyUser()
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

    override fun indentifyUser() {
        providers.forEach { it.indentifyUser() }
    }

    override fun track(event: TrackEvent) {
        providers.forEach { it.track(event) }
    }
}
