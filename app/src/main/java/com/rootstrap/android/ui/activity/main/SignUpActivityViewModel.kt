package com.rootstrap.android.ui.activity.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.rootstrap.android.network.managers.UserManager
import com.rootstrap.android.network.models.User
import com.rootstrap.android.ui.base.BaseViewModel
import com.rootstrap.android.util.NetworkState
import com.rootstrap.android.util.ViewModelListener
import kotlinx.coroutines.launch

open class SignUpActivityViewModel(listener: ViewModelListener?) : BaseViewModel(listener) {

    private val manager = UserManager

    var state: SignUpState = SignUpState.none
        set(value) {
            field = value
            listener?.updateState()
        }

    fun signUp(user: User) {
        networkState = NetworkState.loading
        viewModelScope.launch {
            val result = kotlin.runCatching { manager.signUp(user = user) }
            result.onSuccess {
                networkState = NetworkState.idle
                state = SignUpState.signedUpSuccess
            }.onFailure {
                error = it.message
                networkState = NetworkState.idle
                networkState = NetworkState.error
            }
        }
    }
}

enum class SignUpState {
    signedUpFailure,
    signedUpSuccess,
    none,
}

class SignUpActivityViewModelFactory(var listener: ViewModelListener?) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SignUpActivityViewModel(listener) as T
    }
}
