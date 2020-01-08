package com.rootstrap.android.ui.activity.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.rootstrap.android.R
import com.rootstrap.android.metrics.Analytics
import com.rootstrap.android.metrics.PageEvents
import com.rootstrap.android.metrics.VISIT_MAIN
import com.rootstrap.android.network.managers.SessionManager
import com.rootstrap.android.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : BaseActivity() {

    private lateinit var viewModel: ProfileActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val factory = ProfileActivityViewModelFactory(this)
        viewModel = ViewModelProviders.of(this, factory)
            .get(ProfileActivityViewModel::class.java)

        // Sample
        Analytics.track(PageEvents.visit(VISIT_MAIN))

        welcome_text_view.text = getString(R.string.welcome_message, SessionManager.user?.firstName)
    }
}
