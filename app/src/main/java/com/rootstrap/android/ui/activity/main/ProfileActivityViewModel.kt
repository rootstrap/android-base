package com.rootstrap.android.ui.activity.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rootstrap.android.network.managers.UserManager
import com.rootstrap.android.ui.base.BaseViewModel
import com.rootstrap.android.ui.view.ProfileView
import com.rootstrap.android.util.extensions.ErrorEvent
import com.rootstrap.android.util.extensions.FailureEvent
import com.squareup.otto.Subscribe

open class ProfileActivityViewModel(var view: ProfileView) : BaseViewModel(view) {

    private val manager = UserManager

    fun signOut() {
        view.showProgress()
        manager.signOut()
    }

    @Subscribe
    fun signedOutSuccessfully(event: UserManager.SignedOutSuccessfullyEvent) {
        view.hideProgress()
        view.goToFirstScreen()
    }

    @Subscribe
    fun signOutError(event: ErrorEvent) {
        view.hideProgress()
        view.showError(event.error)
    }

    @Subscribe
    fun signOutFailure(event: FailureEvent) {
        view.hideProgress()
        view.showError(null)
    }
}

class ProfileActivityViewModelFactory(var view: ProfileView) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileActivityViewModel(view) as T
    }
}
