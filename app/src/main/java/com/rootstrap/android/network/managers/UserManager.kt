package com.rootstrap.android.network.managers

import androidx.annotation.RestrictTo
import com.rootstrap.android.network.models.User
import com.rootstrap.android.network.models.UserSerializer
import com.rootstrap.android.network.providers.ServiceProvider
import com.rootstrap.android.network.services.ApiService
import com.rootstrap.android.util.extensions.ActionCallback

/**
 * Singleton Object
 * */
object UserManager {

    private var service = ServiceProvider.create(ApiService::class.java)

    suspend fun signUp(user: User): Result<UserSerializer> =
        ActionCallback.call(service.signUp(UserSerializer(user)))

    suspend fun signIn(user: User): Result<UserSerializer> =
        ActionCallback.call(service.signIn(UserSerializer(user)))

    suspend fun signOut() = ActionCallback.call(service.signOut())

    @RestrictTo(RestrictTo.Scope.TESTS)
    fun reloadService(url: String) {
        service = ServiceProvider.create(ApiService::class.java, url)
    }
}
