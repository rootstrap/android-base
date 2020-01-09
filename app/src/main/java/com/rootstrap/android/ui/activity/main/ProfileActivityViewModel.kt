package com.rootstrap.android.ui.activity.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rootstrap.android.ui.base.BaseViewModel

open class ProfileActivityViewModel(var view: ProfileActivity) : BaseViewModel(view) {
    //TODO add methods
}

class ProfileActivityViewModelFactory(var view: ProfileActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileActivityViewModel(view) as T
    }
}
