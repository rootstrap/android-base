package com.rootstrap.android.ui.activity.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rootstrap.android.network.managers.UserManager
import com.rootstrap.android.network.models.User
import com.rootstrap.android.ui.base.BaseViewModel
import com.rootstrap.android.util.NetworkState
import com.rootstrap.android.util.ViewModelListener
import com.rootstrap.android.util.extensions.ErrorEvent
import com.rootstrap.android.util.extensions.FailureEvent
import com.squareup.otto.Subscribe

open class SignInActivityViewModel(listener: ViewModelListener?) : BaseViewModel(listener) {

    private val manager = UserManager

    var state: SignInState = SignInState.none
        set(value) {
            field = value
            listener?.updateState()
        }

    fun signIn(user: User) {
        networkState = NetworkState.loading
        manager.signIn(user)
    }

    @Subscribe
    fun signedInSuccessfully(event: UserManager.SignInSuccessfullyEvent) {
        networkState = NetworkState.idle
        state = SignInState.signedInSuccess
    }

    @Subscribe
    fun signedInError(event: ErrorEvent) {
        error = event.error
        networkState = NetworkState.idle
        networkState = NetworkState.error
    }

    @Subscribe
    fun signedInFailure(event: FailureEvent) {
        error = null
        networkState = NetworkState.idle
        state = SignInState.signedInFailure
    }
}

enum class SignInState {
    signedInFailure,
    signedInSuccess,
    none,
}

class SignInActivityViewModelFactory(var listener: ViewModelListener?) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SignInActivityViewModel(listener) as T
    }
}
