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
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : BaseActivity() {

    private lateinit var viewModel: SignUpActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val factory = SignUpActivityViewModelFactory(this)
        viewModel = ViewModelProviders.of(this, factory)
            .get(SignUpActivityViewModel::class.java)

        // Sample
        Analytics.track(PageEvents.visit(VISIT_MAIN))

        sign_up_button.setOnClickListener { signUp() }
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

    private fun signUp() {
        val user = User(
            email = email_edit_text.value(),
            firstName = first_name_edit_text.value(),
            lastName = last_name_edit_text.value(),
            password = password_edit_text.value()
        )
        viewModel.signUp(user)
    }
}
