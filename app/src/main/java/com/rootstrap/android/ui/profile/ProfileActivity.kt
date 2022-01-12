package com.rootstrap.android.ui.profile

import android.os.Bundle
import androidx.lifecycle.Observer
import com.rootstrap.android.R
import com.rootstrap.android.databinding.ActivityProfileBinding
import com.rootstrap.android.ui.base.BaseActivity
import com.rootstrap.android.ui.login.SignUpActivity
import com.rootstrap.android.util.NetworkState
import com.rootstrap.data.managers.session.SessionManager
import com.rootstrap.data.metrics.Analytics
import com.rootstrap.data.metrics.PageEvents
import com.rootstrap.data.metrics.VISIT_PROFILE
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileActivity : BaseActivity() {

    private val sessionManager: SessionManager by inject()

    private val viewModel: ProfileActivityViewModel by viewModel()
    private val binding by lazy { ActivityProfileBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Analytics.track(PageEvents.visit(VISIT_PROFILE))

        with(binding) {
            setContentView(root)
            welcomeTextView.text =
                getString(R.string.welcome_message, sessionManager.user?.firstName)
            signOutButton.setOnClickListener { viewModel.signOut() }
        }

        setObservers()
    }

    private fun goToFirstScreen() {
        startActivityClearTask(SignUpActivity())
    }

    private fun setObservers() {
        viewModel.state.observe(this, Observer {
            when (it) {
                ProfileState.SIGN_OUT_FAILURE -> showError(viewModel.error)
                ProfileState.SIGN_OUT_SUCCESS -> goToFirstScreen()
            }
        })

        viewModel.networkState.observe(this, Observer {
            when (it) {
                NetworkState.LOADING -> showProgress()
                NetworkState.IDLE -> hideProgress()
                else -> showError(viewModel.error ?: getString(R.string.default_error))
            }
        })
    }
}
