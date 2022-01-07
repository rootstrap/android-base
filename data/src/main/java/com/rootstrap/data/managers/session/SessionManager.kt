package com.rootstrap.data.managers.session

import com.rootstrap.data.dto.response.User

interface SessionManager {
    var user: User?
    fun addAuthenticationHeaders(accessToken: String, client: String, uid: String)
    fun signOut()
    fun signIn(user: User)
    fun isUserSignedIn(): Boolean
}
