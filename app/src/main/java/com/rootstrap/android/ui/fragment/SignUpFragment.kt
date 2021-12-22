package com.rootstrap.android.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.rootstrap.android.R
import com.rootstrap.android.databinding.FragmentSignUpBinding
import com.rootstrap.android.metrics.Analytics
import com.rootstrap.android.metrics.PageEvents
import com.rootstrap.android.metrics.VISIT_SIGN_UP
import com.rootstrap.android.network.models.User
import com.rootstrap.android.ui.activity.MainActivity
import com.rootstrap.android.ui.activity.main.SignUpActivityViewModel
import com.rootstrap.android.ui.activity.main.SignUpState
import com.rootstrap.android.ui.base.BaseFragment
import com.rootstrap.android.util.extensions.value

class SignUpFragment : BaseFragment() {

    private val viewModel: SignUpActivityViewModel by viewModels()

    private val binding: FragmentSignUpBinding by lazy {
        FragmentSignUpBinding.inflate(layoutInflater)
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
        Analytics.track(PageEvents.visit(VISIT_SIGN_UP))
        setUpView()
        lifecycle.addObserver(viewModel)
        setObservers()
    }

    private fun setUpView() {
        with(binding) {
            signUpButton.setOnClickListener { signUp() }
            signInTextView.setOnClickListener { navigateTo(R.id.action_nav_to_sign_in) }
        }
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
        with(viewModel) {
            state.observe(requireActivity(), {
                when (it) {
                    SignUpState.signUpFailure -> showError(error)
                    SignUpState.signUpSuccess -> openActivity(MainActivity::class.java, true)
                }
            })
            observeNetwork(this)
        }
    }
}
