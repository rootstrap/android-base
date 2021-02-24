package com.rootstrap.android.network.managers.session

import com.rootstrap.android.network.models.User

interface SessionManager {
    var user: User?
    fun addAuthenticationHeaders(accessToken: String, client: String, uid: String)
    fun signOut()
    fun signIn(user: User)
    fun isUserSignedIn(): Boolean
}
