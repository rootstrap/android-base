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
import kotlinx.coroutines.launch

open class ProfileActivityViewModel(
    private val signOut: SignOut,
    private val sessionManager: SessionManager,
) : BaseViewModel() {

    private val _state = MutableLiveData<ProfileState>()
    val state: LiveData<ProfileState>
        get() = _state

    fun signOut() {
        _networkState.value = NetworkState.loading
        viewModelScope.launch {
            when (val result = signOut.invoke()) {
                is DataResult.Success -> {
                    _networkState.value = NetworkState.idle
                    _state.value = ProfileState.signOutSuccess
                    sessionManager.signOut()
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
        _state.value = ProfileState.signOutFailure
    }
}

enum class ProfileState {
    signOutFailure,
    signOutSuccess
}
