package com.rootstrap.android.ui.activity.main

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.rootstrap.android.R
import com.rootstrap.android.metrics.Analytics
import com.rootstrap.android.metrics.PageEvents
import com.rootstrap.android.metrics.VISIT_SIGN_UP
import com.rootstrap.android.network.models.User
import com.rootstrap.android.ui.base.BaseActivity
import com.rootstrap.android.ui.view.AuthView
import com.rootstrap.android.util.NetworkState
import com.rootstrap.android.util.ViewModelListener
import com.rootstrap.android.util.extensions.value
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : BaseActivity(), AuthView {

    private lateinit var viewModel: SignUpActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        Analytics.track(PageEvents.visit(VISIT_SIGN_UP))

        val factory = SignUpActivityViewModelFactory(viewModelListener)
        viewModel = ViewModelProviders.of(this, factory)
            .get(SignUpActivityViewModel::class.java)

        sign_up_button.setOnClickListener { signUp() }
        sign_in_text_view.setOnClickListener { startActivity(Intent(this, SignInActivity::class.java)) }

        lifecycle.addObserver(viewModel)
    }

    override fun showProfile() {
        startActivityClearTask(ProfileActivity())
    }

    private fun signUp() {
        val user = User(
            email = email_edit_text.value(),
            firstName = first_name_edit_text.value(),
            lastName = last_name_edit_text.value(),
            password = password_edit_text.value()
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
