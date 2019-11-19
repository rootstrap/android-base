package com.rootstrap.android.metrics.base

/**
 * Add custom metrics
 * */
interface BaseAnalytics {
    fun addProvider(provider: Provider)
    fun indentifyUser()
    fun track(event: TrackEvent)
    fun addOrEditProperty(property: UserProperty)
    fun addOrEditProperties(properties: List<UserProperty>)
}
