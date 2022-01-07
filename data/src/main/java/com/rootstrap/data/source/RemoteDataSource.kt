package com.rootstrap.data.source

import com.rootstrap.data.dto.request.UserSignInRequestSerializer
import com.rootstrap.data.dto.request.UserSignUpRequestSerializer
import com.rootstrap.data.dto.response.UserResponseSerializer
import retrofit2.Call

interface RemoteDataSource {

    suspend fun signUp(user: UserSignUpRequestSerializer): Call<UserResponseSerializer>

    suspend fun signIn(user: UserSignInRequestSerializer): Call<UserResponseSerializer>

    suspend fun signOut(): Call<Void>
}
