package com.rootstrap.android.metrics.base

import com.google.gson.Gson
import android.os.Bundle
import org.json.JSONException
import org.json.JSONObject

interface Metrics {

    fun track(action: TrackEvent)

    fun reset()

    fun init()

    fun addOrEditUserSuperProperty(userProperty: UserProperty)
}

class UserProperty(val propertyName: String, val propertyValue: String) {
    fun toJsonObject() : JSONObject{
        return JSONObject(Gson().toJson(this))
    }
}

class TrackEvent(val eventName: String, val eventData: Any? = null) {
    fun actionDataToJsonObject() : JSONObject{
        return JSONObject(Gson().toJson(eventData))
    }

    @Throws(JSONException::class)
    fun actionDataToBundle(): Bundle {
        val jsonObject = actionDataToJsonObject()
        val bundle = Bundle()
        val iterator = jsonObject.keys()

        while (iterator.hasNext()) {
            val key = iterator.next() as String
            val value = jsonObject.getString(key)
            bundle.putString(key, value)
        }

        return bundle
    }

}
