package com.rootstrap.android.network.services

import com.rootstrap.android.prefs
import java.io.IOException
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor : Interceptor {

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
        return prefs.ACCESS_TOKEN != "" &&
                prefs.CLIENT != "" &&
                prefs.UID != ""
    }
}
