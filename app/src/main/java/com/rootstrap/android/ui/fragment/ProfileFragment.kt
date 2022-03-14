package com.rootstrap.android.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.rootstrap.android.R
import com.rootstrap.android.databinding.FragmentProfileBinding
import com.rootstrap.android.metrics.Analytics
import com.rootstrap.android.metrics.PageEvents
import com.rootstrap.android.metrics.VISIT_PROFILE
import com.rootstrap.android.network.managers.session.SessionManager
import com.rootstrap.android.ui.activity.OnBoardingActivity
import com.rootstrap.android.ui.viewmodel.ProfileViewModel
import com.rootstrap.android.ui.viewmodel.ProfileState
import com.rootstrap.android.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : BaseFragment() {

    @Inject
    lateinit var sessionManager: SessionManager

    private val viewModel: ProfileViewModel by viewModels()

    private val binding by lazy { FragmentProfileBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Analytics.track(PageEvents.visit(VISIT_PROFILE))
        setUpView()
        setObservers()
    }

    private fun setUpView() {
        with(binding) {
            welcomeTextView.text =
                getString(R.string.welcome_message, sessionManager.user?.firstName)
            signOutButton.setOnClickListener { viewModel.signOut() }
        }
    }

    private fun setObservers() {
        with(viewModel) {
            lifecycle.addObserver(this)
            state.observe(viewLifecycleOwner, {
                when (it) {
                    ProfileState.signOutFailure -> showError(error)
                    ProfileState.signOutSuccess -> openActivity(OnBoardingActivity::class.java, true)
                }
            })
            observeNetwork(this)
        }
    }
}
