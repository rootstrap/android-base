package com.rootstrap.android.ui.login

import android.content.Intent
import android.os.Bundle
import com.rootstrap.android.R
import com.rootstrap.android.databinding.ActivitySignUpBinding
import com.rootstrap.android.ui.base.BaseActivity
import com.rootstrap.android.ui.profile.ProfileActivity
import com.rootstrap.android.util.NetworkState
import com.rootstrap.android.util.extensions.value
import com.rootstrap.data.dto.request.UserSignUpRequest
import com.rootstrap.data.metrics.Analytics
import com.rootstrap.data.metrics.PageEvents
import com.rootstrap.data.metrics.VISIT_SIGN_UP
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity : BaseActivity() {

    private val viewModel: SignUpActivityViewModel by viewModel()
    private val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Analytics.track(PageEvents.visit(VISIT_SIGN_UP))

        with(binding) {
            signUpButton.setOnClickListener { signUp() }
            signInTextView.setOnClickListener { signIn() }
        }

        setObservers()
    }

    private fun showProfile() {
        startActivityClearTask(ProfileActivity())
    }

    private fun signIn() {
        startActivity(Intent(this, SignInActivity::class.java))
    }

    private fun signUp() {
        with(binding) {
            val user = UserSignUpRequest(
                email = emailEditText.value(),
                firstName = firstNameEditText.value(),
                lastName = lastNameEditText.value(),
                password = passwordEditText.value()
            )
            viewModel.signUp(user)
        }
    }

    private fun setObservers() {
        viewModel.state.observe(this) {
            when (it) {
                SignUpState.SIGN_UP_FAILURE -> showError(viewModel.error)
                SignUpState.SIGN_UP_SUCCESS -> showProfile()
            }
        }

        viewModel.networkState.observe(this) {
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
}
