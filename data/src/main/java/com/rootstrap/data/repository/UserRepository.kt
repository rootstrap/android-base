package com.rootstrap.data.repository

import com.rootstrap.data.api.ApiProvider
import com.rootstrap.data.dto.request.UserSignInRequestSerializer
import com.rootstrap.data.dto.request.UserSignUpRequestSerializer
import com.rootstrap.data.util.extensions.ActionCallback

class UserRepository(
    private val apiProvider: ApiProvider
) {

    suspend fun signUp(userSignUpRequestSerializer: UserSignUpRequestSerializer) =
        ActionCallback.call(
            apiProvider.getUserApiService().signUp(userSignUpRequestSerializer)
        )

    suspend fun signIn(userSignInRequestSerializer: UserSignInRequestSerializer) =
        ActionCallback.call(
            apiProvider.getUserApiService().signIn(userSignInRequestSerializer)
        )

    suspend fun signOut() = ActionCallback.call(
        apiProvider.getUserApiService().signOut()
    )
}
