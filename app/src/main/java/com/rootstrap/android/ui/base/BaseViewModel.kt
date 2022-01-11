package com.rootstrap.android.ui.base

import androidx.lifecycle.*
import com.rootstrap.android.util.NetworkState

/**
 * A [ViewModel] base class
 * implement app general LiveData as Session or User
 * **/
open class BaseViewModel : ViewModel() {
    var error: String? = null

    protected val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

  /*  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun register() = Bus().register(this)

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun unregister() = Bus().unregister(this) */
}
