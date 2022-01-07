package com.rootstrap.data.api.endpoints

import com.rootstrap.data.dto.request.UserSignInRequestSerializer
import com.rootstrap.data.dto.request.UserSignUpRequestSerializer
import com.rootstrap.data.dto.response.UserResponseSerializer
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST

interface UserApi {

    @POST("users/")
    fun signUp(@Body user: UserSignUpRequestSerializer): Call<UserResponseSerializer>

    @POST("users/sign_in")
    fun signIn(@Body user: UserSignInRequestSerializer): Call<UserResponseSerializer>

    @DELETE("users/sign_out")
    fun signOut(): Call<Void>
}
