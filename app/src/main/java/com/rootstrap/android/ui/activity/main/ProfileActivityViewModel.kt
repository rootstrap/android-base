package com.rootstrap.android.ui.activity.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.rootstrap.android.network.managers.session.SessionManager
import com.rootstrap.android.network.managers.user.UserManager
import com.rootstrap.android.ui.base.BaseViewModel
import com.rootstrap.android.util.NetworkState
import com.rootstrap.android.util.extensions.ApiErrorType
import com.rootstrap.android.util.extensions.ApiException
import kotlinx.coroutines.launch

open class ProfileActivityViewModel @ViewModelInject constructor(
    private val sessionManager: SessionManager,
    private val userManager: UserManager
) : BaseViewModel() {

    fun signOut() {
        networkState = NetworkState.loading
        viewModelScope.launch {
            val result = userManager.signOut()
            if (result.isSuccess) {
                networkState = NetworkState.idle
                state = ProfileState.signOutSuccess
                sessionManager.signOut()
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
        state = ProfileState.signOutFailure
    }

    var state: ProfileState = ProfileState.none
        set(value) {
            field = value
            // listener?.updateState()
        }
}

enum class ProfileState {
    signOutFailure,
    signOutSuccess,
    none,
}
