package com.rootstrap.data.repository

import com.rootstrap.data.dto.request.UserSignInRequestSerializer
import com.rootstrap.data.dto.request.UserSignUpRequestSerializer
import com.rootstrap.data.source.RemoteDataSource
import com.rootstrap.data.util.extensions.ActionCallback

class UserRepository(
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun signUp(userSignUpRequestSerializer: UserSignUpRequestSerializer) = ActionCallback.call(
        remoteDataSource.signUp(userSignUpRequestSerializer)
    )

    suspend fun signIn(userSignInRequestSerializer: UserSignInRequestSerializer) = ActionCallback.call(
        remoteDataSource.signIn(userSignInRequestSerializer)
    )

    suspend fun signOut() = ActionCallback.call(
        remoteDataSource.signOut()
    )
}
