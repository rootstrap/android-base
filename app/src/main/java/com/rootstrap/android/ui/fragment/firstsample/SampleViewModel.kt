package com.rootstrap.android.ui.fragment.firstsample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rootstrap.android.ui.base.BaseViewModel

open class Sample1ViewModel(var view: SampleFragment) : BaseViewModel() {
    // TODO implement methods
}

class Sample1ViewModelFactory(var view: SampleFragment) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return Sample1ViewModel(view) as T
    }
}
