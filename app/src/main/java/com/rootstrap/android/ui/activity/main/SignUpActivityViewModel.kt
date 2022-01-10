package com.rootstrap.android.ui.activity.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rootstrap.android.ui.base.BaseViewModel
import com.rootstrap.android.util.NetworkState
import com.rootstrap.data.api.ApiException
import com.rootstrap.data.dto.request.UserSignUpRequest
import com.rootstrap.data.dto.request.UserSignUpRequestSerializer
import com.rootstrap.data.dto.response.DataResult
import com.rootstrap.data.managers.session.SessionManager

import com.rootstrap.usecases.SignUp
import kotlinx.coroutines.launch

open class SignUpActivityViewModel(
    private val signUp: SignUp,
    private val sessionManager: SessionManager,
) : BaseViewModel() {

    private val _state = MutableLiveData<SignUpState>()
    val state: LiveData<SignUpState>
        get() = _state

    fun signUp(userSignUpRequest: UserSignUpRequest) {
        _networkState.value = NetworkState.loading
        viewModelScope.launch {

            when (val result = signUp.invoke(UserSignUpRequestSerializer(userSignUpRequest))) {
                is DataResult.Success -> {
                    result.data?.user?.let { user ->
                        sessionManager.signIn(user)
                    } ?: handleError(ApiException(null))

                    _networkState.value = NetworkState.idle
                    _state.value = SignUpState.SIGN_UP_SUCCESS
                }
                is DataResult.Error -> {
                    handleError(result.exception)
                }
            }
        }
    }

    private fun handleError(exception: ApiException?) {
        error = exception?.message
        _networkState.value = NetworkState.error
        _state.value = SignUpState.SIGN_UP_FAILURE
    }
}

enum class SignUpState {
    SIGN_UP_FAILURE,
    SIGN_UP_SUCCESS
}
