package com.rootstrap.android.ui.activity.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.rootstrap.android.R
import com.rootstrap.android.databinding.ActivityProfileBinding
import com.rootstrap.android.metrics.Analytics
import com.rootstrap.android.metrics.PageEvents
import com.rootstrap.android.metrics.VISIT_PROFILE
import com.rootstrap.android.network.managers.session.SessionManager
import com.rootstrap.android.ui.base.BaseActivity
import com.rootstrap.android.ui.view.ProfileView
import com.rootstrap.android.util.NetworkState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileActivity : BaseActivity(), ProfileView {

    @Inject lateinit var sessionManager: SessionManager

    private val viewModel: ProfileActivityViewModel by viewModels()
    private val binding by lazy { ActivityProfileBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Analytics.track(PageEvents.visit(VISIT_PROFILE))

        with(binding) {
            setContentView(root)
            welcomeTextView.text = getString(R.string.welcome_message, sessionManager.user?.firstName)
            signOutButton.setOnClickListener { viewModel.signOut() }
        }

        lifecycle.addObserver(viewModel)
        setObservers()
    }

    override fun goToFirstScreen() {
        startActivityClearTask(SignUpActivity())
    }

    private fun setObservers() {
        viewModel.state.observe(this, Observer {
            when (it) {
                ProfileState.signOutFailure -> showError(viewModel.error)
                ProfileState.signOutSuccess -> goToFirstScreen()
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
