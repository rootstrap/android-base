package com.rootstrap.android.ui.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ViewDataBinding
import com.rootstrap.android.App
import com.rootstrap.android.R
import com.rootstrap.android.models.Session
import com.rootstrap.android.utils.SimpleCallBack

abstract class BaseViewModel : ViewModel() {

    abstract fun addBindingView(bindingView: ViewDataBinding?)
    var session : MutableLiveData<Session> = App.mE.mObservableSession!!
    var error : MutableLiveData<APP_ERROR> = MutableLiveData()

}

enum class APP_ERROR(var message: Int) {
    SIMPLE(R.string.error_simple)
}
