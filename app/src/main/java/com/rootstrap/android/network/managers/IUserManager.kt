package com.rootstrap.android.network.managers

import com.rootstrap.android.network.models.User
import com.rootstrap.android.network.models.UserSerializer
import com.rootstrap.android.util.extensions.Data

interface IUserManager {
    suspend fun signUp(user: User): Result<Data<UserSerializer>>
    suspend fun signIn(user: User): Result<Data<UserSerializer>>
    suspend fun signOut(): Result<Data<Void>>
}
