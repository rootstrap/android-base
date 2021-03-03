package com.rootstrap.android.network.services

import com.rootstrap.android.util.Prefs
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class AuthenticationInterceptor @Inject constructor(private val prefs: Prefs) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        if (hasHeaders()) {
            builder
                .addHeader(prefs.ACCESS_TOKEN, prefs.accessToken)
                .addHeader(prefs.CLIENT, prefs.client)
                .addHeader(prefs.UID, prefs.uid)
        }

        return chain.proceed(builder.build())
    }

    private fun hasHeaders(): Boolean {
        return prefs.accessToken != "" &&
                prefs.client != "" &&
                prefs.uid != ""
    }
}
