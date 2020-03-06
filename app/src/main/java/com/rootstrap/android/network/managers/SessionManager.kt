package com.rootstrap.android.network.managers

import com.rootstrap.android.network.models.User
import com.rootstrap.android.prefs

object SessionManager {

    var user: User? = prefs.user
        set(value) {
            field = value
            prefs.user = value
        }

    fun addAuthenticationHeaders(accessToken: String, client: String, uid: String) {
        prefs.accessToken = accessToken
        prefs.client = client
        prefs.uid = uid
    }

    fun signOut() {
        user = null
        prefs.clear()
    }

    fun signIn(user: User) {
        this.user = user
        prefs.user = user
        prefs.signedIn = true
    }

    fun isUserSignedIn(): Boolean {
        return (user != null && prefs.signedIn)
    }
}
