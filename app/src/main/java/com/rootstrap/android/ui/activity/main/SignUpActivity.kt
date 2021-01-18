package com.rootstrap.android.ui.activity.main

import android.content.Intent
import android.os.Bundle
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

class SignUpActivity : BaseActivity(), AuthView {

    private lateinit var viewModel: SignUpActivityViewModel
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)

        setContentView(binding.root)
        Analytics.track(PageEvents.visit(VISIT_SIGN_UP))

        val factory = SignUpActivityViewModelFactory(viewModelListener)
        viewModel = ViewModelProvider(this, factory)
            .get(SignUpActivityViewModel::class.java)

        binding.signUpButton.setOnClickListener { signUp() }
        binding.signInTextView.setOnClickListener { startActivity(Intent(this, SignInActivity::class.java)) }

        lifecycle.addObserver(viewModel)
    }

    override fun showProfile() {
        startActivityClearTask(ProfileActivity())
    }

    private fun signUp() {
        val user = User(
            email = binding.emailEditText.value(),
            firstName = binding.firstNameEditText.value(),
            lastName = binding.lastNameEditText.value(),
            password = binding.passwordEditText.value()
        )
        viewModel.signUp(user)
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
