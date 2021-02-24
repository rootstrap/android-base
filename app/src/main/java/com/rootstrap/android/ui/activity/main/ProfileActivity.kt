package com.rootstrap.android.ui.activity.main

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rootstrap.android.R
import com.rootstrap.android.metrics.Analytics
import com.rootstrap.android.metrics.PageEvents
import com.rootstrap.android.metrics.VISIT_PROFILE
import com.rootstrap.android.network.managers.SessionManager
import com.rootstrap.android.ui.base.BaseActivity
import com.rootstrap.android.ui.view.ProfileView
import com.rootstrap.android.util.NetworkState
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : BaseActivity(), ProfileView {

    private lateinit var viewModel: ProfileActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        viewModel = ViewModelProvider(this)
            .get(ProfileActivityViewModel::class.java)

        Analytics.track(PageEvents.visit(VISIT_PROFILE))

        welcome_text_view.text = getString(R.string.welcome_message, SessionManager.user?.firstName)
        sign_out_button.setOnClickListener { viewModel.signOut() }

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
