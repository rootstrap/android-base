package com.rootstrap.android.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rootstrap.android.ui.base.BaseViewModel
import com.rootstrap.android.util.NetworkState
import com.rootstrap.android.util.dispatcher.DispatcherProvider
import com.rootstrap.data.api.ApiException
import com.rootstrap.data.dto.response.DataResult
import com.rootstrap.data.managers.session.SessionManager
import com.rootstrap.usecases.SignOut
import kotlinx.coroutines.launch

open class ProfileActivityViewModel(
    private val signOut: SignOut,
    private val sessionManager: SessionManager,
    private val dispatcherProvider: DispatcherProvider
) : BaseViewModel() {

    private val _state = MutableLiveData<ProfileState>()
    val state: LiveData<ProfileState>
        get() = _state

    fun signOut() {
        _networkState.value = NetworkState.LOADING
        viewModelScope.launch(dispatcherProvider.io) {
            when (val result = signOut.invoke()) {
                is DataResult.Success -> {
                    restoreNetworkState()
                    _state.postValue(ProfileState.SIGN_OUT_SUCCESS)
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
        _networkState.postValue(NetworkState.ERROR)
        _state.postValue(ProfileState.SIGN_OUT_FAILURE)
    }

    private fun restoreNetworkState() {
        _networkState.postValue(NetworkState.IDLE)
    }
}

enum class ProfileState {
    SIGN_OUT_FAILURE,
    SIGN_OUT_SUCCESS
}
