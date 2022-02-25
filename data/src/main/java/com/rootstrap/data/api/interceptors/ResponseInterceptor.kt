package com.rootstrap.data.api.interceptors

import com.rootstrap.data.managers.session.SessionManager
import com.rootstrap.data.util.Prefs
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ResponseInterceptor(
    private val prefs: Prefs,
    private val sessionManager: SessionManager
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        prefer(response)
        return response
    }

    private fun prefer(response: Response) {
        val accessToken = response.header(Prefs.ACCESS_TOKEN)
        val client = response.header(Prefs.CLIENT)
        val uid = response.header(Prefs.UID)
        if (preferValid(accessToken, client, uid))
            sessionManager.addAuthenticationHeaders(accessToken!!, client!!, uid!!)
    }

    private fun preferValid(accessToken: String?, client: String?, uid: String?): Boolean {
        return accessToken != null && accessToken.isNotEmpty() &&
                client != null && client.isNotEmpty() &&
                uid != null && uid.isNotEmpty()
    }
}
