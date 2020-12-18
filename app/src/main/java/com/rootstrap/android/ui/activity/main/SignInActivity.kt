package com.rootstrap.android.ui.activity.main

import android.Manifest
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.rootstrap.android.R
import com.rootstrap.android.metrics.Analytics
import com.rootstrap.android.metrics.PageEvents
import com.rootstrap.android.metrics.VISIT_SIGN_IN
import com.rootstrap.android.network.models.User
import com.rootstrap.android.ui.view.AuthView
import com.rootstrap.android.util.NetworkState
import com.rootstrap.android.util.ViewModelListener
import com.rootstrap.android.util.extensions.value
import com.rootstrap.android.util.permissions.PermissionActivity
import com.rootstrap.android.util.permissions.PermissionResponse
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : PermissionActivity(), AuthView {

    private lateinit var viewModel: SignInActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        Analytics.track(PageEvents.visit(VISIT_SIGN_IN))

        val factory = SignInActivityViewModelFactory(viewModelListener)
        viewModel = ViewModelProviders.of(this, factory)
            .get(SignInActivityViewModel::class.java)

        sign_in_button.setOnClickListener { signIn() }

        lifecycle.addObserver(viewModel)

        sampleAskForPermission()
    }

    override fun showProfile() {
        startActivityClearTask(ProfileActivity())
    }

    private fun signIn() {
        val user = User(
            email = email_edit_text.value(),
            password = password_edit_text.value()
        )
        viewModel.signIn(user)
    }

    // ViewModelListener
    private val viewModelListener = object : ViewModelListener {
        override fun updateState() {
            when (viewModel.state) {
                SignInState.signInFailure -> showError(viewModel.error)
                SignInState.signInSuccess -> showProfile()
                else -> {
                }
            }
        }

        override fun updateNetworkState() {
            when (viewModel.networkState) {
                NetworkState.loading -> showProgress()
                NetworkState.idle -> hideProgress()
                else -> showError(viewModel.error ?: getString(R.string.default_error))
            }
        }
    }

    fun sampleAskForPermission() {
        requestPermission(arrayOf(Manifest.permission.CAMERA), object : PermissionResponse {
            override fun granted() {
                // TODO..
            }

            override fun denied() {
                // TODO..
            }

            override fun foreverDenied() {
                // TODO..
            }
        })
    }
}
