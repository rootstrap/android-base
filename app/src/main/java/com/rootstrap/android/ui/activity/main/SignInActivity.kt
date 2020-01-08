package com.rootstrap.android.ui.activity.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.rootstrap.android.R
import com.rootstrap.android.metrics.Analytics
import com.rootstrap.android.metrics.PageEvents
import com.rootstrap.android.metrics.VISIT_MAIN
import com.rootstrap.android.network.models.User
import com.rootstrap.android.ui.base.BaseActivity
import com.rootstrap.android.util.extensions.value
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : BaseActivity() {

    private lateinit var viewModel: SignInActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val factory = SignInActivityViewModelFactory(this)
        viewModel = ViewModelProviders.of(this, factory)
            .get(SignInActivityViewModel::class.java)

        // Sample
        Analytics.track(PageEvents.visit(VISIT_MAIN))

        sign_in_button.setOnClickListener { signIn() }
    }

    override fun onResume() {
        super.onResume()
        viewModel.register()
    }

    override fun onPause() {
        super.onPause()
        viewModel.unregister()
    }

    fun showProfile() {
        startActivityClearTask(ProfileActivity())
    }

    private fun signIn() {
        val user = User(
            email = email_edit_text.value(),
            password = password_edit_text.value()
        )
        viewModel.signIn(user)
    }
}
