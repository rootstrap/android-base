package com.rootstrap.android.ui.activity.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rootstrap.android.network.managers.UserManager
import com.rootstrap.android.network.models.User
import com.rootstrap.android.ui.base.BaseViewModel
import com.rootstrap.android.ui.view.AuthView
import com.rootstrap.android.util.extensions.ErrorEvent
import com.rootstrap.android.util.extensions.FailureEvent
import com.squareup.otto.Subscribe

open class SignInActivityViewModel(var view: AuthView) : BaseViewModel(view) {

    private val manager = UserManager

    fun signIn(user: User) {
        view.showProgress()
        manager.signIn(user)
    }

    @Subscribe
    fun signedInSuccessfully(event: UserManager.SignInSuccessfullyEvent) {
        view.hideProgress()
        view.showProfile()
    }

    @Subscribe
    fun signedInError(event: ErrorEvent) {
        view.hideProgress()
        view.showError(event.error)
    }

    @Subscribe
    fun signedInFailure(event: FailureEvent) {
        view.hideProgress()
        view.showError(null)
    }
}

class SignInActivityViewModelFactory(var view: AuthView) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SignInActivityViewModel(view) as T
    }
}
