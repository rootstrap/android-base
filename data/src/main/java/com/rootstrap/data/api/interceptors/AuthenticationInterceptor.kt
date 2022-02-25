package com.rootstrap.data.api.interceptors

import com.rootstrap.data.util.Prefs
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthenticationInterceptor(private val prefs: Prefs) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        if (hasHeaders()) {
            builder
                .addHeader(Prefs.ACCESS_TOKEN, prefs.accessToken)
                .addHeader(Prefs.CLIENT, prefs.client)
                .addHeader(Prefs.UID, prefs.uid)
        }

        return chain.proceed(builder.build())
    }

    private fun hasHeaders(): Boolean {
        return prefs.accessToken != "" &&
                prefs.client != "" &&
                prefs.uid != ""
    }
}
