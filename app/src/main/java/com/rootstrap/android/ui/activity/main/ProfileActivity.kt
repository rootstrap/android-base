package com.rootstrap.android.ui.activity.main

import android.os.Bundle
import androidx.activity.viewModels
import com.rootstrap.android.R
import com.rootstrap.android.metrics.Analytics
import com.rootstrap.android.metrics.PageEvents
import com.rootstrap.android.metrics.VISIT_PROFILE
import com.rootstrap.android.network.managers.SessionManager
import com.rootstrap.android.ui.base.BaseActivity
import com.rootstrap.android.ui.view.ProfileView
import com.rootstrap.android.util.NetworkState
import com.rootstrap.android.util.ViewModelListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_profile.*

@AndroidEntryPoint
class ProfileActivity : BaseActivity(), ProfileView {

    private val viewModel: ProfileActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        Analytics.track(PageEvents.visit(VISIT_PROFILE))

        welcome_text_view.text = getString(R.string.welcome_message, SessionManager.user?.firstName)
        sign_out_button.setOnClickListener { viewModel.signOut() }

        lifecycle.addObserver(viewModel)
    }

    override fun goToFirstScreen() {
        startActivityClearTask(SignUpActivity())
    }

    // ViewModelListener
    private val viewModelListener = object : ViewModelListener {
        override fun updateState() {
            when (viewModel.state) {
                ProfileState.signOutFailure -> showError(viewModel.error)
                ProfileState.signOutSuccess -> goToFirstScreen()
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
