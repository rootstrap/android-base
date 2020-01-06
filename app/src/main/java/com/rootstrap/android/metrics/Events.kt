package com.rootstrap.android.metrics

import com.rootstrap.android.metrics.base.TrackEvent

class UserEvents {
    companion object {
        /**
         * Analytics login event
         * @param data [Any] Optional
         * **/
        fun login(data: Any? = null): TrackEvent {
            // You can do something before create the event for example
            // create a super property with the current user info
            // appAnalytics.addOrEditProperty(UserProperty("user_name", "Fulano"))
            // the same for the other events
            Analytics.identifyUser()
            return TrackEvent(LOGIN, data)
        }

        /**
         * Analytics logout event
         * @param data [Any] Optional
         * **/
        fun logout(data: Any? = null) = TrackEvent(LOGOUT, data)

        /**
         * Analytics signup event
         * @param data [Any] Optional
         * **/
        fun signup(data: Any? = null) = TrackEvent(SIGNUP, data)
    }
}

class PageEvents {
    companion object {
        /**
         * Analytics visit page event
         * @param data [Any] Optional
         * **/
        fun visit(page: String, data: Any? = null) = TrackEvent(page, data)
    }
}
