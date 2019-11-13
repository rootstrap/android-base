package com.rootstrap.android.ui.fragment.firstsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.rootstrap.android.R
import com.rootstrap.android.ui.base.BaseFragment

class SampleFragment : BaseFragment() {

    private lateinit var viewModel: Sample1ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sample, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = Sample1ViewModelFactory(this)
        viewModel = ViewModelProviders.of(this, factory)
            .get(Sample1ViewModel::class.java)
    }
}
