package com.rootstrap.android.metrics

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.rootstrap.android.metrics.base.Provider
import com.rootstrap.android.metrics.base.TrackEvent
import com.rootstrap.android.metrics.base.UserProperty
import org.json.JSONException

/**
 * Reference: https://firebase.google.com/docs/analytics/android/start
 * */
class GoogleAnalytics(context: Context) : Provider {
    var analytic: FirebaseAnalytics = FirebaseAnalytics.getInstance(context)

    /**
     * Track any event in the app
     * @param event App Event to track
     * */
    override fun track(event: TrackEvent) {
        analytic.let {
            if (event.eventName.isNotEmpty()) {
                event.eventData?.let { _ ->
                    try {
                        it.logEvent(event.eventName, event.actionDataToBundle())
                    } catch (e: JSONException) {
                        it.logEvent(event.eventName, null)
                        e.printStackTrace()
                    }
                } ?: it.logEvent(event.eventName, null)
            }
        }
    }

    /**
     * Reset analytic
     */
    override fun reset() {
        analytic.resetAnalyticsData()
    }

    /**
     * Initialize google analytics user and default data
     * analytic.setUserId(userId)
     * */
    override fun identifyUser() {
        // TODO see the comments ↑↑↑
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
        analytic.setUserProperty(userProperty.propertyName, userProperty.propertyValue)
    }
}
