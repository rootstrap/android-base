package com.rootstrap.android.ui.activity.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.rootstrap.android.network.managers.IUserManager
import com.rootstrap.android.network.managers.SessionManager
import com.rootstrap.android.network.managers.UserManager
import com.rootstrap.android.network.models.User
import com.rootstrap.android.ui.base.BaseViewModel
import com.rootstrap.android.util.NetworkState
import com.rootstrap.android.util.ViewModelListener
import com.rootstrap.android.util.extensions.ApiErrorType
import com.rootstrap.android.util.extensions.ApiException
import kotlinx.coroutines.launch

open class SignUpActivityViewModel(listener: ViewModelListener?) : BaseViewModel(listener) {

    private val manager: IUserManager = UserManager()

    var state: SignUpState = SignUpState.none
        set(value) {
            field = value
            listener?.updateState()
        }

    fun signUp(user: User) {
        networkState = NetworkState.loading
        viewModelScope.launch {
            val result = manager.signUp(user = user)

            if (result.isSuccess) {
                result.getOrNull()?.value?.user?.let { user ->
                    SessionManager.signIn(user)
                }

                networkState = NetworkState.idle
                state = SignUpState.signedUpSuccess
            } else {
                mangeError(result.exceptionOrNull())
            }
        }
    }

    private fun mangeError(exception: Throwable?) {
        error = if (exception is ApiException && exception.errorType == ApiErrorType.apiError) {
            exception.message
        } else null

        networkState = NetworkState.idle
        networkState = NetworkState.error
        state = SignUpState.signedUpFailure
    }
}

enum class SignUpState {
    signedUpFailure,
    signedUpSuccess,
    none,
}

class SignUpActivityViewModelFactory(var listener: ViewModelListener?) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SignUpActivityViewModel(listener) as T
    }
}
