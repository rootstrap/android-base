package com.rootstrap.android.ui.activity.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.rootstrap.android.network.managers.IUserManager
import com.rootstrap.android.network.managers.SessionManager
import com.rootstrap.android.network.managers.UserManager
import com.rootstrap.android.ui.base.BaseViewModel
import com.rootstrap.android.util.NetworkState
import com.rootstrap.android.util.ViewModelListener
import com.rootstrap.android.util.extensions.ApiErrorType
import com.rootstrap.android.util.extensions.ApiException
import kotlinx.coroutines.launch

open class ProfileActivityViewModel(listener: ViewModelListener?) : BaseViewModel(listener) {

    private val manager: IUserManager = UserManager

    fun signOut() {
        networkState = NetworkState.loading
        viewModelScope.launch {
            val result = manager.signOut()
            if (result.isSuccess) {
                networkState = NetworkState.idle
                state = ProfileState.signOutSuccess
                SessionManager.signOut()
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
            listener?.updateState()
        }
}

enum class ProfileState {
    signOutFailure,
    signOutSuccess,
    none,
}

class ProfileActivityViewModelFactory(var listener: ViewModelListener?) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileActivityViewModel(listener) as T
    }
}
