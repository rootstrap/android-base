package com.rootstrap.android.infraestructure.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class InterceptorHeaders : Interceptor {

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