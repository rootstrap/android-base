package com.rootstrap.android.ui.activity.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.rootstrap.android.R
import com.rootstrap.android.metrics.Analytics
import com.rootstrap.android.metrics.PageEvents
import com.rootstrap.android.metrics.VISIT_MAIN
import com.rootstrap.android.ui.base.BaseActivity

class MainActivity : BaseActivity() {
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory = MainActivityViewModelFactory(this)
        viewModel = ViewModelProviders.of(this, factory)
            .get(MainActivityViewModel::class.java)

        // Sample
        Analytics.track(PageEvents.visit(VISIT_MAIN))
    }
}
