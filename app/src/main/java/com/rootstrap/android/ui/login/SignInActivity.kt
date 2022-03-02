package com.rootstrap.android.ui.login

import android.Manifest
import android.os.Bundle
import com.rootstrap.android.R
import com.rootstrap.android.databinding.ActivitySignInBinding
import com.rootstrap.android.ui.profile.ProfileActivity
import com.rootstrap.android.util.NetworkState
import com.rootstrap.android.util.extensions.value
import com.rootstrap.android.util.permissions.PermissionActivity
import com.rootstrap.android.util.permissions.PermissionResponse
import com.rootstrap.data.dto.request.UserSignInRequest
import com.rootstrap.data.metrics.Analytics
import com.rootstrap.data.metrics.PageEvents
import com.rootstrap.data.metrics.VISIT_SIGN_IN
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInActivity : PermissionActivity() {

    private val viewModel: SignInActivityViewModel by viewModel()
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Analytics.track(PageEvents.visit(VISIT_SIGN_IN))

        binding.signInButton.setOnClickListener { signIn() }

        setObservers()
        sampleAskForPermission()
    }

    private fun showProfile() {
        startActivityClearTask(ProfileActivity())
    }

    private fun signIn() {
        with(binding) {
            val user = UserSignInRequest(
                email = emailEditText.value(),
                password = passwordEditText.value()
            )
            viewModel.signIn(user)
        }
    }

    private fun setObservers() {
        viewModel.state.observe(
            this
        ) {
            it?.run {
                when (this) {
                    SignInState.SIGN_IN_FAILURE -> showError(viewModel.error)
                    SignInState.SIGN_IN_SUCCESS -> showProfile()
                }
            }
        }

        viewModel.networkState.observe(
            this
        ) {
            when (it) {
                NetworkState.LOADING -> showProgress()
                NetworkState.IDLE -> hideProgress()
                else -> {
                    hideProgress()
                    showError(viewModel.error ?: getString(R.string.default_error))
                }
            }
        }
    }

    private fun sampleAskForPermission() {
        requestPermission(
            arrayOf(Manifest.permission.CAMERA),
            object : PermissionResponse {
                override fun granted() {
                    // TODO..
                }

                override fun denied() {
                    // TODO..
                }

                override fun foreverDenied() {
                    // TODO..
                }
            }
        )
    }
}
