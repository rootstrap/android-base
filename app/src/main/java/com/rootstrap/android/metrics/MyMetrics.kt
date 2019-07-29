package com.rootstrap.android.metrics

import android.annotation.SuppressLint
import android.content.Context
import com.rootstrap.android.R
import com.rootstrap.android.metrics.base.AppMetrics
import com.rootstrap.android.metrics.base.Metrics
import com.rootstrap.android.metrics.base.TrackEvent

val appMetric: AppMetrics by lazy {
    MyMetrics.instance
}

class MyMetrics(context: Context, var metrics: Metrics? = null) : AppMetrics {

    companion object {
        fun with(context: Context) {
            instance = MyMetrics(context)
        }

        @SuppressLint("StaticFieldLeak")
        lateinit var instance: AppMetrics
            private set
    }

    init {
        //Firebase Analytics
        metrics = GoogleAnalytics(context)
        //MixPanel
        metrics = MixPanelAnalytics(context)
    }

    override fun logOut() {
        metrics!!.reset()
    }

    override fun logIn() {
        metrics!!.init()
    }

    override fun signUp() {
        metrics!!.init()
    }

    override fun visitPage(pageName: String, data: Any) {
        metrics!!.track(TrackEvent(pageName, data))
    }
}
