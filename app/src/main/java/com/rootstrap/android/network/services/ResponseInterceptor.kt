package com.rootstrap.android.network.services

import com.rootstrap.android.network.managers.SessionManager
import com.rootstrap.android.prefs
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ResponseInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        prefer(response)
        return response
    }

    private fun prefer(response: Response) {
        val accessToken = response.header(prefs.ACCESS_TOKEN)
        val client = response.header(prefs.CLIENT)
        val uid = response.header(prefs.UID)
        if (preferValid(accessToken, client, uid))
            SessionManager.addAuthenticationHeaders(accessToken!!, client!!, uid!!)
    }

    private fun preferValid(accessToken: String?, client: String?, uid: String?): Boolean {
        return accessToken != null && !accessToken.isEmpty() &&
                client != null && !client.isEmpty() &&
                uid != null && !uid.isEmpty()
    }
}
