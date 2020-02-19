package com.rootstrap.android.network.managers

import androidx.annotation.RestrictTo
import com.rootstrap.android.bus
import com.rootstrap.android.network.models.User
import com.rootstrap.android.network.models.UserSerializer
import com.rootstrap.android.network.providers.ServiceProvider
import com.rootstrap.android.network.services.ApiService
import com.rootstrap.android.util.extensions.ActionCallback
import retrofit2.Response

/**
 * Singleton Object
 * */
object UserManager {

    private var service = ServiceProvider.create(ApiService::class.java)

    fun signUp(user: User) {
        val userSerializer = UserSerializer(user)
        val signUp = service.signUp(userSerializer)
        signUp.enqueue(UserCallback())
    }

    fun signIn(user: User) {
        val userSerializer = UserSerializer(user)
        val signIn = service.signIn(userSerializer)
        signIn.enqueue(LogInCallback())
    }

    fun signOut() {
        val signOut = service.signOut()
        signOut.enqueue(LogOutCallback())
    }

    private class UserCallback : ActionCallback<UserSerializer>() {
        override fun responseAction(response: Response<UserSerializer>) {
            super.responseAction(response)
            response.body()?.let {
                SessionManager.user = it.user
            }
            bus.post(UserCreatedSuccessfullyEvent())
        }
    }

    private class LogInCallback : ActionCallback<UserSerializer>() {

        override fun responseAction(response: Response<UserSerializer>) {
            super.responseAction(response)
            response.body()?.let {
                SessionManager.signIn(it.user)
            }
            bus.post(SignInSuccessfullyEvent())
        }
    }

    private class LogOutCallback : ActionCallback<Void>() {

        override fun responseAction(response: Response<Void>) {
            super.responseAction(response)

            SessionManager.signOut()
            bus.post(SignedOutSuccessfullyEvent())
        }
    }

    @RestrictTo(RestrictTo.Scope.TESTS)
    open fun reloadService(url: String) {
        service = ServiceProvider.create(ApiService::class.java, url)
    }

    class UserCreatedSuccessfullyEvent
    class SignInSuccessfullyEvent
    class SignedOutSuccessfullyEvent
}
