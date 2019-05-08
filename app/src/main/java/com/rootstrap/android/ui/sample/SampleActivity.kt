package com.rootstrap.android.ui.sample

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.rootstrap.android.ui.base.BaseActivity
import com.rootstrap.android.databinding.ActivityLogBinding

/**
 * @author Amaury Ricardo Miranda 2019
 * */

//This is a simple activity to show how to use MVVM with android data binding
//references https://developer.android.com/topic/libraries/data-binding#kotlin
//for now i skip room database......

class SampleActivity : BaseActivity() {

    var viewModel : LogViewModel? = null
    var bindingView : ActivityLogBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingView = DataBindingUtil.setContentView(this, com.rootstrap.android.R.layout.activity_log)

        viewModel = ViewModelProviders.of(this).get(LogViewModel::class.java)
        viewModel!!.addBindingView(bindingView!!)

        super.useViewModel(viewModel)
    }

    override fun onCloseSession() {
        TODO() //do something when the section is close
        super.onCloseSession()
    }

    override fun onUpdateSession() {
        TODO() //do something when the section is update
        super.onUpdateSession()
    }
}
