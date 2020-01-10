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

open class SignUpActivityViewModel(var view: AuthView) : BaseViewModel(view) {

    private val manager = UserManager()

    fun signUp(user: User) {
        view.showProgress()
        manager.signUp(user)
    }

    @Subscribe
    fun signedUpSuccessfully(event: UserManager.UserCreatedSuccessfullyEvent) {
        view.hideProgress()
        view.showProfile()
    }

    @Subscribe
    fun signedUpError(event: ErrorEvent) {
        view.hideProgress()
        view.showError(event.error)
    }

    @Subscribe
    fun signedUpFailure(event: FailureEvent) {
        view.hideProgress()
        view.showError(null)
    }
}

class SignUpActivityViewModelFactory(var view: AuthView) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SignUpActivityViewModel(view) as T
    }
}
