package com.rootstrap.data.api.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!WifiService.instance.isOnline()) {
            throw IOException("No internet connection")
        } else {
            return chain.proceed(chain.request())
        }
    }
}
