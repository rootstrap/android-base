package com.rootstrap.android.network.services

import java.io.IOException
import okhttp3.Interceptor
import okhttp3.Response

class HeadersInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build()

        return chain.proceed(request)
    }
}
