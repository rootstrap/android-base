package com.rootstrap.android.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.rootstrap.android.databinding.FragmentSignInBinding
import com.rootstrap.android.metrics.Analytics
import com.rootstrap.android.metrics.PageEvents
import com.rootstrap.android.metrics.VISIT_SIGN_IN
import com.rootstrap.android.network.models.User
import com.rootstrap.android.ui.activity.MainActivity
import com.rootstrap.android.ui.activity.main.SignInActivityViewModel
import com.rootstrap.android.ui.activity.main.SignInState
import com.rootstrap.android.ui.base.BaseFragment
import com.rootstrap.android.util.extensions.value

class SignInFragment : BaseFragment() {

    private val viewModel: SignInActivityViewModel by viewModels()

    private val binding: FragmentSignInBinding by lazy {
        FragmentSignInBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Analytics.track(PageEvents.visit(VISIT_SIGN_IN))
        setUpView()
        setObservers()
    }

    private fun setUpView() {
        binding.signInButton.setOnClickListener { signIn() }
    }

    private fun signIn() {
        with(binding) {
            val user = User(
                email = emailEditText.value(),
                password = passwordEditText.value()
            )
            viewModel.signIn(user)
        }
    }

    private fun setObservers() {
        with(viewModel) {
            lifecycle.addObserver(this)
            state.observe(requireActivity(), {
                when (it) {
                    SignInState.signInFailure -> showError(error)
                    SignInState.signInSuccess -> openActivity(MainActivity::class.java)
                }
            })
            observeNetwork(this)
        }
    }
}
