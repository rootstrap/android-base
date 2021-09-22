package com.rootstrap.android.network.managers.user

import com.rootstrap.android.BuildConfig
import com.rootstrap.android.network.models.UserSerializer
import com.rootstrap.android.network.services.AuthenticationInterceptor
import com.rootstrap.android.network.services.HeadersInterceptor
import com.rootstrap.android.network.services.ResponseInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class UserManagerRetrofitBuilder(
    authenticationInterceptor: AuthenticationInterceptor,
    responseInterceptor: ResponseInterceptor
) {

    private val httpInterceptorLevel = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
    else HttpLoggingInterceptor.Level.BASIC

    private val okHttpClient = OkHttpClient()
        .newBuilder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(HeadersInterceptor())
        .addInterceptor(authenticationInterceptor)
        .addInterceptor(responseInterceptor)
        .addInterceptor(HttpLoggingInterceptor().setLevel(httpInterceptorLevel))
        .build()

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val buildAuthRetrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi).withNullSerialization())
        .client(okHttpClient)
        .build()

    private val api = buildAuthRetrofit.create(UserManagerService::class.java)

    fun signIn(user: UserSerializer): Call<UserSerializer> {
        return api.signIn(user)
    }

    fun signOut(): Call<Void> {
        return api.signOut()
    }

    fun signUp(user: UserSerializer): Call<UserSerializer> {
        return api.signUp(user)
    }
}
