package com.rootstrap.android.ui.activity.main

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
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
import com.rootstrap.android.util.extensions.value

class SignUpActivity : BaseActivity(), AuthView {

    private lateinit var viewModel: SignUpActivityViewModel
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)

        setContentView(binding.root)
        Analytics.track(PageEvents.visit(VISIT_SIGN_UP))

        viewModel = ViewModelProvider(this)
            .get(SignUpActivityViewModel::class.java)

        with(binding) {
            signUpButton.setOnClickListener { signUp() }
            signInTextView.setOnClickListener { signIn() }
        }
        lifecycle.addObserver(viewModel)
        setObservers()
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

    private fun setObservers() {
        viewModel.state.observe(this, Observer {
            when (it) {
                SignUpState.signUpFailure -> showError(viewModel.error)
                SignUpState.signUpSuccess -> showProfile()
            }
        })

        viewModel.networkState.observe(this, Observer {
            when (it) {
                NetworkState.loading -> showProgress()
                NetworkState.idle -> hideProgress()
                else -> showError(viewModel.error ?: getString(R.string.default_error))
            }
        })
    }
}
