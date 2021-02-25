package com.rootstrap.android.ui.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.rootstrap.android.bus
import com.rootstrap.android.util.NetworkState

/**
 * A [ViewModel] base class
 * implement app general LiveData as Session or User
 * **/
open class BaseViewModel : ViewModel(), LifecycleObserver {
    var error: String? = null

    protected val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun register() = bus.register(this)

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun unregister() = bus.unregister(this)
}
