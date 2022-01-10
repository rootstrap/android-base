package com.rootstrap.android.ui.activity.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rootstrap.android.ui.base.BaseViewModel
import com.rootstrap.android.util.NetworkState
import com.rootstrap.data.api.ApiException
import com.rootstrap.data.dto.request.UserSignInRequest
import com.rootstrap.data.dto.request.UserSignInRequestSerializer
import com.rootstrap.data.dto.response.DataResult
import com.rootstrap.data.managers.session.SessionManager
import com.rootstrap.usecases.SignIn
import kotlinx.coroutines.launch

open class SignInActivityViewModel(
    private val signIn: SignIn,
    private val sessionManager: SessionManager,
) : BaseViewModel() {

    private val _state = MutableLiveData<SignInState>()
    val state: LiveData<SignInState>
        get() = _state

    fun signIn(user: UserSignInRequest) {
        _networkState.value = NetworkState.loading
        viewModelScope.launch {
            when (val result = signIn.invoke(UserSignInRequestSerializer(user))) {
                is DataResult.Success -> {
                    result.data?.user?.let { user ->
                        sessionManager.signIn(user)
                    }

                    _networkState.value = NetworkState.idle
                    _state.value = SignInState.signInSuccess
                }
                is DataResult.Error ->
                    handleError(result.exception)
            }
        }
    }

    private fun handleError(exception: ApiException?) {
        error = exception?.message
        _networkState.value = NetworkState.error
        _state.value = SignInState.signInFailure
    }
}

enum class SignInState {
    signInFailure,
    signInSuccess
}
