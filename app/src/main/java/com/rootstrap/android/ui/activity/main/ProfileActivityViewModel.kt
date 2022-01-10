package com.rootstrap.android.ui.activity.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rootstrap.android.ui.base.BaseViewModel
import com.rootstrap.android.util.NetworkState
import com.rootstrap.data.api.ApiException
import com.rootstrap.data.dto.response.DataResult
import com.rootstrap.data.managers.session.SessionManager
import com.rootstrap.usecases.SignOut
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

open class ProfileActivityViewModel(
    private val signOut: SignOut,
    private val sessionManager: SessionManager,
    uiDispatcher: CoroutineDispatcher
) : BaseViewModel(uiDispatcher) {

    private val _state = MutableLiveData<ProfileState>()
    val state: LiveData<ProfileState>
        get() = _state

    fun signOut() {
        _networkState.value = NetworkState.LOADING
        viewModelScope.launch {
            when (val result = signOut.invoke()) {
                is DataResult.Success -> {
                    restoreNetworkState()
                    _state.value = ProfileState.SIGN_OUT_SUCCESS
                    sessionManager.signOut()
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
        _networkState.value = NetworkState.ERROR
        _state.value = ProfileState.SIGN_OUT_FAILURE
    }

    private fun restoreNetworkState() {
        _networkState.value = NetworkState.IDLE
    }
}

enum class ProfileState {
    SIGN_OUT_FAILURE,
    SIGN_OUT_SUCCESS
}
