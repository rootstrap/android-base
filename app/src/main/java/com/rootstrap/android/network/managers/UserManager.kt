package com.rootstrap.android.network.managers

import com.rootstrap.android.bus
import com.rootstrap.android.network.models.User
import com.rootstrap.android.network.models.UserSerializer
import com.rootstrap.android.network.providers.ServiceProvider
import com.rootstrap.android.network.services.ApiService
import com.rootstrap.android.util.extensions.ActionCallback
import retrofit2.Response

class UserManager {

    fun signUp(email: String, phone: String, username: String, password: String) {
        val userSerializer =
            UserSerializer(User(email = email, name = username, phone = phone, password = password))

        val service = ServiceProvider.create(ApiService::class.java)
        val signUp = service.signUp(userSerializer)
        signUp.enqueue(UserCallback())
    }

    fun signIn(email: String, password: String) {
        val userSerializer = UserSerializer(User(email = email, password = password))

        val service = ServiceProvider.create(ApiService::class.java)
        val signIn = service.signIn(userSerializer)
        signIn.enqueue(LogInCallback())
    }

    private inner class UserCallback : ActionCallback<UserSerializer>() {

        override fun responseAction(response: Response<UserSerializer>) {
            super.responseAction(response)
            response.body()?.let {
                SessionManager.user = it.user
            }
            bus.post(UserCreatedSuccessfullyEvent())
        }
    }

    private inner class LogInCallback : ActionCallback<UserSerializer>() {

        override fun responseAction(response: Response<UserSerializer>) {
            super.responseAction(response)
            response.body()?.let {
                SessionManager.signIn(it.user)
            }
            bus.post(SignInSuccessfullyEvent())
        }
    }

    class UserCreatedSuccessfullyEvent
    class SignInSuccessfullyEvent
}
