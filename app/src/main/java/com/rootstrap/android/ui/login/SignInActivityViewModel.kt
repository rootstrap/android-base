package com.rootstrap.android.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rootstrap.android.ui.base.BaseViewModel
import com.rootstrap.android.util.NetworkState
import com.rootstrap.android.util.dispatcher.DispatcherProvider
import com.rootstrap.data.api.ApiException
import com.rootstrap.data.dto.request.UserSignInRequest
import com.rootstrap.data.dto.request.UserSignInRequestSerializer
import com.rootstrap.data.dto.response.DataResult
import com.rootstrap.data.dto.response.toDomainUser
import com.rootstrap.data.managers.session.SessionManager
import com.rootstrap.usecases.SignIn
import kotlinx.coroutines.launch

open class SignInActivityViewModel(
    private val signIn: SignIn,
    private val sessionManager: SessionManager,
    private val dispatcherProvider: DispatcherProvider
) : BaseViewModel() {

    private val _state = MutableLiveData<SignInState>()
    val state: LiveData<SignInState>
        get() = _state

    fun signIn(user: UserSignInRequest) {
        _networkState.value = NetworkState.LOADING
        viewModelScope.launch(dispatcherProvider.io) {
            when (val result = signIn.invoke(UserSignInRequestSerializer(user))) {
                is DataResult.Success -> {
                    result.data?.user?.let { user ->
                        sessionManager.signIn(user.toDomainUser())
                    }

                    restoreNetworkState()
                    _state.postValue(SignInState.SIGN_IN_SUCCESS)
                }
                is DataResult.Error -> {
                    handleError(result.exception)
                    restoreNetworkState()
                }
            }
        }
    }

    private fun handleError(exception: ApiException?) {
        error = exception?.message
        _networkState.postValue(NetworkState.ERROR)
        _state.postValue(SignInState.SIGN_IN_FAILURE)
    }

    private fun restoreNetworkState() {
        _networkState.postValue(NetworkState.IDLE)
    }
}

enum class SignInState {
    SIGN_IN_FAILURE,
    SIGN_IN_SUCCESS
}
