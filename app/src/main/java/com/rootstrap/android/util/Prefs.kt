package com.rootstrap.android.util

import android.content.SharedPreferences
import com.google.gson.Gson
import com.rootstrap.android.network.models.User
import com.rootstrap.android.util.extensions.fromJson
import javax.inject.Inject

class Prefs @Inject constructor(private val prefs: SharedPreferences) {

    val ACCESS_TOKEN = "access-token"
    val CLIENT = "Client"
    val UID = "uid"
    val USER = "user"
    val SIGNED_IN = "signed_in"

    private val gson: Gson = Gson()

    var accessToken: String
        get() = prefs.getString(ACCESS_TOKEN, "")!!
        set(value) = prefs.edit().putString(ACCESS_TOKEN, value).apply()

    var client: String
        get() = prefs.getString(CLIENT, "")!!
        set(value) = prefs.edit().putString(CLIENT, value).apply()

    var uid: String
        get() = prefs.getString(UID, "")!!
        set(value) = prefs.edit().putString(UID, value).apply()

    var user: User?
        get() = gson.fromJson<User>(prefs.getString(USER, "")!!)
        set(value) = prefs.edit().putString(USER, gson.toJson(value)).apply()

    var signedIn: Boolean
        get() = prefs.getBoolean(SIGNED_IN, false)
        set(value) = prefs.edit().putBoolean(SIGNED_IN, value).apply()

    fun clear() = prefs.edit().clear().apply()
}
