package com.rootstrap.android.network.managers.user

import com.rootstrap.android.network.models.User
import com.rootstrap.android.network.models.UserSerializer
import com.rootstrap.android.util.extensions.ActionCallback
import com.rootstrap.android.util.extensions.Data

/**
 * Singleton class
 * */
class UserManagerImpl(private val apiService: UserManagerRetrofitBuilder) : UserManager {

    override suspend fun signUp(user: User): Result<Data<UserSerializer>> =
        ActionCallback.call(apiService.signUp(UserSerializer(user)))

    override suspend fun signIn(user: User): Result<Data<UserSerializer>> =
        ActionCallback.call(apiService.signIn(UserSerializer(user)))

    override suspend fun signOut(): Result<Data<Void>> =
        ActionCallback.call(apiService.signOut())
}
