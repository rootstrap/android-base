package com.rootstrap.android.network.managers.session

import com.rootstrap.android.network.models.User
import com.rootstrap.android.util.Prefs
import javax.inject.Inject

class SessionManagerImpl @Inject constructor(private val prefs: Prefs) : SessionManager {

    override var user: User? = prefs.user
        set(value) {
            field = value
            prefs.user = value
        }

    override fun addAuthenticationHeaders(accessToken: String, client: String, uid: String) {
        prefs.accessToken = accessToken
        prefs.client = client
        prefs.uid = uid
    }

    override fun signOut() {
        user = null
        prefs.clear()
    }

    override fun signIn(user: User) {
        this.user = user
        prefs.user = user
        prefs.signedIn = true
    }

    override fun isUserSignedIn(): Boolean {
        return (user != null && prefs.signedIn)
    }
}
