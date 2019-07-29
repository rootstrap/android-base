package com.rootstrap.android.metrics.base

/**
 * Add custom metrics
 * */
interface BaseAnalytics {
    fun addProvider(provider: Provider)
    fun logOut()
    fun logIn()
    fun signUp()
    fun visitPage(pageName: String, data: Any) //activity or fragment
}
