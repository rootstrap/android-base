package com.rootstrap.android.network.managers

import androidx.annotation.RestrictTo
import com.rootstrap.android.network.models.User
import com.rootstrap.android.network.models.UserSerializer
import com.rootstrap.android.network.providers.ServiceProvider
import com.rootstrap.android.network.services.ApiService
import com.rootstrap.android.util.extensions.ActionCallback
import com.rootstrap.android.util.extensions.Data

/**
 * Singleton Object
 * */
object UserManager : IUserManager {

    private var service = ServiceProvider.create(ApiService::class.java)

    override suspend fun signUp(user: User): Result<Data<UserSerializer>> =
        ActionCallback.call(service.signUp(UserSerializer(user)))

    override suspend fun signIn(user: User): Result<Data<UserSerializer>> =
        ActionCallback.call(service.signIn(UserSerializer(user)))

    override suspend fun signOut(): Result<Data<Void>> =
        ActionCallback.call(service.signOut())

    @RestrictTo(RestrictTo.Scope.TESTS)
    fun reloadService(url: String) {
        service = ServiceProvider.create(ApiService::class.java, url)
    }
}
