package com.rootstrap.android.metrics

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.rootstrap.android.metrics.base.Metrics
import com.rootstrap.android.metrics.base.TrackEvent
import com.rootstrap.android.metrics.base.UserProperty
import org.json.JSONException

/**
 * Reference: https://firebase.google.com/docs/analytics/android/start
 * */
class GoogleAnalytics(context: Context, var analytic: FirebaseAnalytics? = null ) : Metrics {

    init {
        analytic = FirebaseAnalytics.getInstance(context)
        init()
    }

    /**
     * Track any event in the app
     * @param event App Event to track
     * */
    override fun track(event: TrackEvent) {
        if (event.eventName.isNotEmpty()) {
            if (event.eventData != null) {
                try {
                    analytic!!.logEvent(event.eventName, event.actionDataToBundle())
                } catch (e: JSONException) {
                    analytic!!.logEvent(event.eventName, null)
                    e.printStackTrace()
                }
            } else {
                analytic!!.logEvent(event.eventName, null)
            }
        }
    }

    /**
     * Reset analytic
     */
    override fun reset() {
        analytic!!.resetAnalyticsData()
    }

    /**
     * Initialize google analytics user and default data
     * analytic.setUserId(userId)
     * */
    override fun init() {

    }

    /**
     * @param userProperty
     * Sample
     * UserProperty("Type","Admin")
     * UserProperty("Type","Customer")
     * UserProperty("Type","Anonymous")
     * https://firebase.google.com/docs/analytics/android/properties
     * */
    override fun addOrEditUserSuperProperty(userProperty: UserProperty) {
        analytic!!.setUserProperty(userProperty.propertyName, userProperty.propertyValue)
    }
}
