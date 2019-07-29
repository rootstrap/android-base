package com.rootstrap.android.metrics.base

/**
 * Add custom metrics
 * How use:
 * in the Application class -> onCreate
 * MyMetrics.with(applicationContext)
 * then use in the place you want to track the event (sample):
 * appMetric.logIn()
 * */
interface AppMetrics {
    fun logOut()
    fun logIn()
    fun signUp()
    fun visitPage(pageName: String, data: Any) //activity or fragment
}
