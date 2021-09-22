package com.rootstrap.android.util

import android.content.SharedPreferences
import androidx.annotation.VisibleForTesting
import com.google.gson.Gson
import com.rootstrap.android.network.models.User
import com.rootstrap.android.util.extensions.fromJson

class Prefs(private val prefs: SharedPreferences) {

    val ACCESS_TOKEN = "access-token"
    val CLIENT = "Client"
    val UID = "uid"
    val USER = "user"
    val SIGNED_IN = "signed_in"

    private val gson: Gson = Gson()

    var accessToken: String
        get() = getPref().getString(ACCESS_TOKEN, "")!!
        set(value) = getPref().edit().putString(ACCESS_TOKEN, value).apply()

    var client: String
        get() = getPref().getString(CLIENT, "")!!
        set(value) = getPref().edit().putString(CLIENT, value).apply()

    var uid: String
        get() = getPref().getString(UID, "")!!
        set(value) = getPref().edit().putString(UID, value).apply()

    var user: User?
        get() = gson.fromJson<User>(prefs.getString(USER, "")!!)
        set(value) = getPref().edit().putString(USER, gson.toJson(value)).apply()

    var signedIn: Boolean
        get() = getPref().getBoolean(SIGNED_IN, false)
        set(value) = getPref().edit().putBoolean(SIGNED_IN, value).apply()

    fun clear() = getPref().edit().clear().apply()

    @VisibleForTesting
    private fun getPref() = prefs
}
