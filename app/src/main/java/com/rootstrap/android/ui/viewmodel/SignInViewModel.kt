package com.rootstrap.android.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rootstrap.android.network.managers.session.SessionManager
import com.rootstrap.android.network.managers.user.UserManager
import com.rootstrap.android.network.models.User
import com.rootstrap.android.ui.base.BaseViewModel
import com.rootstrap.android.util.NetworkState
import com.rootstrap.android.util.dispatcher.DispatcherProvider
import com.rootstrap.android.util.extensions.ApiErrorType
import com.rootstrap.android.util.extensions.ApiException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class SignInViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val userManager: UserManager,
    private val dispatcher: DispatcherProvider
) : BaseViewModel() {

    private val _state = MutableLiveData<SignInState>()
    val state: LiveData<SignInState>
        get() = _state

    fun signIn(user: User) {
        _networkState.value = NetworkState.loading
        // Avoid using hardcoded dispatcher this way can be mocked later
        viewModelScope.launch(dispatcher.io) {
            val result = userManager.signIn(user = user)
            if (result.isSuccess) {
                result.getOrNull()?.value?.user?.let { user ->
                    sessionManager.signIn(user)
                }

                _networkState.value = NetworkState.idle
                _state.value = SignInState.signInSuccess
            } else {
                handleError(result.exceptionOrNull())
            }
        }
    }

    private fun handleError(exception: Throwable?) {
        error = if (exception is ApiException && exception.errorType == ApiErrorType.apiError) {
            exception.message
        } else null

        _networkState.value = NetworkState.error
        _state.value = SignInState.signInFailure
    }
}

enum class SignInState {
    signInFailure,
    signInSuccess
}
