package com.rootstrap.android.ui.base

import androidx.lifecycle.ViewModel
import com.rootstrap.android.bus

/**
 * A [ViewModel] base class
 * implement app general LiveData as Session or User
 * **/
open class BaseViewModel(open var v: BaseView?) : ViewModel() {

    fun register() {
        bus.register(this)
    }

    fun unregister() {
        v = null
        bus.unregister(this)
    }
}
