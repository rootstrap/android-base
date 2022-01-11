package com.rootstrap.data.util

import android.content.SharedPreferences
import com.google.gson.Gson
import com.rootstrap.data.util.extensions.fromJson
import com.rootstrap.domain.User

class Prefs(private val prefs: SharedPreferences) {

    private val gson: Gson = Gson()

    var accessToken: String
        get() = prefs.getString(ACCESS_TOKEN, "")!!
        set(value) = prefs.edit().putString(ACCESS_TOKEN, value).apply()

    var client: String
        get() = prefs.getString(CLIENT, "")!!
        set(value) = prefs.edit().putString(CLIENT, value).apply()

    var uid: String
        get() = prefs.getString(UID, "")!!
        set(value) = prefs.edit().putString(Companion.UID, value).apply()

    var user: User?
        get() = gson.fromJson<User>(prefs.getString(USER, "")!!)
        set(value) = prefs.edit().putString(USER, gson.toJson(value)).apply()

    var signedIn: Boolean
        get() = prefs.getBoolean(SIGNED_IN, false)
        set(value) = prefs.edit().putBoolean(SIGNED_IN, value).apply()

    fun clear() = prefs.edit().clear().apply()

    companion object {
        const val ACCESS_TOKEN = "access-token"
        const val CLIENT = "Client"
        const val UID = "uid"
        const val USER = "user"
        const val SIGNED_IN = "signed_in"
    }
}
