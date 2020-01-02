package com.rootstrap.android.metrics

import android.content.Context

import com.mixpanel.android.mpmetrics.MixpanelAPI
import com.rootstrap.android.R
import com.rootstrap.android.metrics.base.Provider
import com.rootstrap.android.metrics.base.TrackEvent
import com.rootstrap.android.metrics.base.UserProperty
import org.json.JSONException

/**
 * Mix panel android reference: https://developer.mixpanel.com/docs/android
 * */
class MixPanelAnalytics(context: Context) : Provider {
    var analytic: MixpanelAPI =
        MixpanelAPI.getInstance(context, context.getString(R.string.mixpanel_api_key))

    /**
     * Track any event in the app
     * @param event Action to track
     * */
    override fun track(event: TrackEvent) {
        analytic.let {
            if (event.eventName.isNotEmpty()) {
                event.eventData?.let { _ ->
                    try {
                        it.track(event.eventName, event.actionDataToJsonObject())
                    } catch (e: JSONException) {
                        it.track(event.eventName)
                        e.printStackTrace()
                    }
                } ?: it.track(event.eventName)
            }
        }
    }

    /**
     * Reset analytic
     */
    override fun reset() {
        analytic.reset()
    }

    /**
     * Initialize mixpanel user and default data
     *  analytic.identify(user.id)
     *  analytic.alias(user.id, null)
     *  val people = analytic.people
     *  people.identify(user.Id)
     *  people.set("\$first_name", user.username)
     *  people.set("\$email", user.email())
     *  analytic!!.flush()
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
     * https://help.mixpanel.com/hc/en-us/articles/360001355526
     * */
    override fun addOrEditUserSuperProperty(userProperty: UserProperty) {
        analytic.registerSuperProperties(userProperty.toJsonObject())
    }
}
