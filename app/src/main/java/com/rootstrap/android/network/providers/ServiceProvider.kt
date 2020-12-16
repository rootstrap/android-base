package com.rootstrap.android.network.providers

import com.google.gson.Gson
import com.rootstrap.android.BuildConfig
import com.rootstrap.android.network.managers.ApiErrorType
import com.rootstrap.android.network.managers.ApiException
import com.rootstrap.android.network.models.ErrorModel
import com.rootstrap.android.network.services.AuthenticationInterceptor
import com.rootstrap.android.network.services.HeadersInterceptor
import com.rootstrap.android.network.services.ResponseInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ServiceProvider {

    private var URL_API: String? = null

    private fun build(): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(HeadersInterceptor())
            .addInterceptor(AuthenticationInterceptor())
            .addInterceptor(ResponseInterceptor())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .build()

        return Retrofit.Builder()
            .baseUrl(URL_API)
            .addConverterFactory(MoshiConverterFactory.create().withNullSerialization())
            .client(client)
            .build()
    }

    fun <T> create(klass: Class<T>, url: String? = BuildConfig.API_URL): T {
        URL_API = url
        return build().create(klass)
    }
}
