package com.rootstrap.android.ui.activity.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.rootstrap.android.network.managers.session.SessionManager
import com.rootstrap.android.network.managers.user.UserManager
import com.rootstrap.android.network.models.User
import com.rootstrap.android.ui.base.BaseViewModel
import com.rootstrap.android.util.NetworkState
import com.rootstrap.android.util.extensions.ApiErrorType
import com.rootstrap.android.util.extensions.ApiException
import kotlinx.coroutines.launch

open class SignInActivityViewModel @ViewModelInject constructor(
    private val sessionManager: SessionManager,
    private val userManager: UserManager
) : BaseViewModel() {

    var state: SignInState = SignInState.none
        set(value) {
            field = value
            // listener?.updateState()
        }

    fun signIn(user: User) {
        networkState = NetworkState.loading
        viewModelScope.launch {
            val result = userManager.signIn(user = user)
            if (result.isSuccess) {
                result.getOrNull()?.value?.user?.let { user ->
                    sessionManager.signIn(user)
                }

                networkState = NetworkState.idle
                state = SignInState.signInSuccess
            } else {
                handleError(result.exceptionOrNull())
            }
        }
    }

    private fun handleError(exception: Throwable?) {
        error = if (exception is ApiException && exception.errorType == ApiErrorType.apiError) {
            exception.message
        } else null

        networkState = NetworkState.idle
        networkState = NetworkState.error
        state = SignInState.signInFailure
    }
}

enum class SignInState {
    signInFailure,
    signInSuccess,
    none,
}
