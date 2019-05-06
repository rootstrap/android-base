package com.rootstrap.android.infraestructure.connection

import android.content.Context
import com.rootstrap.android.BuildConfig
import com.rootstrap.android.infraestructure.interceptors.InterceptorAuthentication
import com.rootstrap.android.infraestructure.interceptors.InterceptorHeaders
import com.rootstrap.android.infraestructure.interceptors.InterceptorResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConnectionProvider {

    private fun build(context: Context): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(InterceptorHeaders())
            .addInterceptor(InterceptorAuthentication(context.applicationContext))
            .addInterceptor(InterceptorResponse(context.applicationContext))
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun <T> create(context: Context, klass: Class<T>): T {
        return build(context).create(klass)
    }

}
