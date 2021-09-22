package com.rootstrap.android.network.managers.user

import com.rootstrap.android.network.models.UserSerializer
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST

interface UserManagerService {

    @POST("users/")
    fun signUp(@Body user: UserSerializer): Call<UserSerializer>

    @POST("users/sign_in")
    fun signIn(@Body user: UserSerializer): Call<UserSerializer>

    @DELETE("users/sign_out")
    fun signOut(): Call<Void>
}
