package com.rootstrap.android.ui.activity.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rootstrap.android.network.managers.UserManager
import com.rootstrap.android.ui.base.BaseViewModel
import com.rootstrap.android.util.NetworkState
import com.rootstrap.android.util.ViewModelDelegate
import com.rootstrap.android.util.extensions.ErrorEvent
import com.rootstrap.android.util.extensions.FailureEvent
import com.squareup.otto.Subscribe

open class ProfileActivityViewModel(delegate: ViewModelDelegate?) : BaseViewModel(delegate) {

    private val manager = UserManager

    fun signOut() {
        networkState = NetworkState.loading
        manager.signOut()
    }

    var state: ProfileState = ProfileState.none
        set(value) {
            field = value
            delegate?.updateState()
        }

    @Subscribe
    fun signedOutSuccessfully(event: UserManager.SignedOutSuccessfullyEvent) {
        networkState = NetworkState.idle
        state = ProfileState.signedOutSuccessfully
    }

    @Subscribe
    fun signOutError(event: ErrorEvent) {
        error = event.error
        networkState = NetworkState.idle
        networkState = NetworkState.error
    }

    @Subscribe
    fun signOutFailure(event: FailureEvent) {
        error = null
        networkState = NetworkState.idle
        state = ProfileState.signOutFailure
    }
}

enum class ProfileState {
    signOutFailure,
    signedOutSuccessfully,
    none,
}

class ProfileActivityViewModelFactory(var delegate: ViewModelDelegate?) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileActivityViewModel(delegate) as T
    }
}
