package com.rootstrap.data.api

import com.rootstrap.android.data.BuildConfig
import com.rootstrap.data.api.interceptors.AuthenticationInterceptor
import com.rootstrap.data.api.interceptors.HeadersInterceptor
import com.rootstrap.data.api.interceptors.ResponseInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceFactory(
    private val authenticationInterceptor: AuthenticationInterceptor,
    private val headersInterceptor: HeadersInterceptor,
    private val responseInterceptor: ResponseInterceptor
) {

    fun getOkHttpClient(): OkHttpClient {
        val httpInterceptorLevel = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE

        return OkHttpClient.Builder()
                .addInterceptor(authenticationInterceptor)
                .addInterceptor(headersInterceptor)
                .addInterceptor(responseInterceptor)
                .addInterceptor(HttpLoggingInterceptor().setLevel(httpInterceptorLevel))
                .build()
    }

    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
    }

    companion object {
        var URL_API: String? = null
    }
}
