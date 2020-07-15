package com.rootstrap.android.ui.activity.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rootstrap.android.network.managers.UserManager
import com.rootstrap.android.network.models.User
import com.rootstrap.android.ui.base.BaseViewModel
import com.rootstrap.android.util.NetworkState
import com.rootstrap.android.util.ViewModelDelegate
import com.rootstrap.android.util.extensions.ErrorEvent
import com.rootstrap.android.util.extensions.FailureEvent
import com.squareup.otto.Subscribe

open class SignUpActivityViewModel(
    delegate: ViewModelDelegate?
) : BaseViewModel(delegate) {

    private val manager = UserManager

    var state: SignUpState = SignUpState.none
        set(value) {
            field = value
            delegate?.updateState()
        }

    fun signUp(user: User) {
        networkState = NetworkState.loading
        manager.signUp(user)
    }

    @Subscribe
    fun signedUpSuccessfully(event: UserManager.UserCreatedSuccessfullyEvent) {
        networkState = NetworkState.idle
        state = SignUpState.signedUpSuccess
    }

    @Subscribe
    fun signedUpError(event: ErrorEvent) {
        error = event.error
        networkState = NetworkState.idle
        networkState = NetworkState.error
    }

    @Subscribe
    fun signedUpFailure(event: FailureEvent) {
        error = null
        networkState = NetworkState.idle
        state = SignUpState.signedUpFailure
    }
}

enum class SignUpState {
    signedUpFailure,
    signedUpSuccess,
    none,
}

class SignUpActivityViewModelFactory(var delegate: ViewModelDelegate?) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SignUpActivityViewModel(delegate) as T
    }
}
