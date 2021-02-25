package com.rootstrap.android.ui.activity.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rootstrap.android.network.managers.session.SessionManager
import com.rootstrap.android.network.managers.user.UserManager
import com.rootstrap.android.network.models.User
import com.rootstrap.android.ui.base.BaseViewModel
import com.rootstrap.android.util.NetworkState
import com.rootstrap.android.util.extensions.ApiErrorType
import com.rootstrap.android.util.extensions.ApiException
import kotlinx.coroutines.launch

open class SignUpActivityViewModel @ViewModelInject constructor(
    private val sessionManager: SessionManager,
    private val userManager: UserManager
) : BaseViewModel() {

    private val _state = MutableLiveData<SignUpState>()
    val state: LiveData<SignUpState>
        get() = _state

    fun signUp(user: User) {
        _networkState.value = NetworkState.loading
        viewModelScope.launch {
            val result = userManager.signUp(user = user)

            if (result.isSuccess) {
                result.getOrNull()?.value?.user?.let { user ->
                    sessionManager.signIn(user)
                }

                _networkState.value = NetworkState.idle
                _state.value = SignUpState.signUpSuccess
            } else {
                handleError(result.exceptionOrNull())
            }
        }
    }

    private fun handleError(exception: Throwable?) {
        error = if (exception is ApiException && exception.errorType == ApiErrorType.apiError) {
            exception.message
        } else null

        _networkState.value = NetworkState.idle
        _networkState.value = NetworkState.error
        _state.value = SignUpState.signUpFailure
    }
}

enum class SignUpState {
    signUpFailure,
    signUpSuccess
}
