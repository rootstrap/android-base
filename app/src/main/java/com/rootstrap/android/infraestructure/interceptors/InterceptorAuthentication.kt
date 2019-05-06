package com.rootstrap.android.infraestructure.interceptors

import android.content.Context
import com.rootstrap.android.App
import com.rootstrap.android.infraestructure.interceptors.HeadersKey.Companion.ACCESS_TOKEN
import com.rootstrap.android.infraestructure.interceptors.HeadersKey.Companion.CLIENT
import com.rootstrap.android.infraestructure.interceptors.HeadersKey.Companion.UID
import com.rootstrap.android.utils.TinyDB
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class InterceptorAuthentication(private val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val builder = chain.request().newBuilder()
        val preferences = App.mE.mPreferences

        if (addHeaderValid(preferences!!))
            builder
                .addHeader(ACCESS_TOKEN, preferences.getString(ACCESS_TOKEN, "")!!)
                .addHeader(CLIENT, preferences.getString(CLIENT, "")!!)
                .addHeader(UID, preferences.getString(UID, "")!!)

        return chain.proceed(builder.build())
    }

    private fun addHeaderValid(preferences: TinyDB): Boolean = preferences.contains(ACCESS_TOKEN) && preferences.contains(CLIENT) && preferences.contains(UID)

}
