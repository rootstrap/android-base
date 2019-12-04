package com.rootstrap.android.util

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.rootstrap.android.network.models.User
import com.rootstrap.android.util.extensions.fromJson

class Prefs(context: Context) {

    val ACCESS_TOKEN = "access-token"
    val CLIENT = "Client"
    val UID = "uid"
    val USER = "user"
    val SIGNED_IN = "signed_in"

    val prefs: SharedPreferences = PreferenceManager
        .getDefaultSharedPreferences(context)
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

}
