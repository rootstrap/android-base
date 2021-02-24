package com.rootstrap.android.ui.activity.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.rootstrap.android.R
import com.rootstrap.android.databinding.ActivitySignUpBinding
import com.rootstrap.android.metrics.Analytics
import com.rootstrap.android.metrics.PageEvents
import com.rootstrap.android.metrics.VISIT_SIGN_UP
import com.rootstrap.android.network.models.User
import com.rootstrap.android.ui.base.BaseActivity
import com.rootstrap.android.ui.view.AuthView
import com.rootstrap.android.util.NetworkState
import com.rootstrap.android.util.ViewModelListener
import com.rootstrap.android.util.extensions.value
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : BaseActivity(), AuthView {

    private val viewModel: SignUpActivityViewModel by viewModels()
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)

        setContentView(binding.root)
        Analytics.track(PageEvents.visit(VISIT_SIGN_UP))

        with(binding) {
            signUpButton.setOnClickListener { signUp() }
            signInTextView.setOnClickListener { signIn() }
        }
        lifecycle.addObserver(viewModel)
    }

    override fun showProfile() {
        startActivityClearTask(ProfileActivity())
    }

    private fun signIn() {
        startActivity(Intent(this, SignInActivity::class.java))
    }

    private fun signUp() {
        with(binding) {
            val user = User(
                email = emailEditText.value(),
                firstName = firstNameEditText.value(),
                lastName = lastNameEditText.value(),
                password = passwordEditText.value()
            )
            viewModel.signUp(user)
        }
    }

    // ViewModelListener
    private val viewModelListener = object : ViewModelListener {
        override fun updateState() {
            when (viewModel.state) {
                SignUpState.signUpFailure -> showError(viewModel.error)
                SignUpState.signUpSuccess -> showProfile()
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
}
