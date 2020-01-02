package com.rootstrap.android.ui.activity.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rootstrap.android.ui.base.BaseViewModel

open class MainActivityViewModel(var view: MainActivity) : BaseViewModel() {
    // TODO implement methods
}

class MainActivityViewModelFactory(var view: MainActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(view) as T
    }
}
