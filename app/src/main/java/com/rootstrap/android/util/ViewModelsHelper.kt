package com.rootstrap.android.util

interface ViewModelListener {
    fun updateState()
    fun updateNetworkState()
}

enum class NetworkState {
    loading,
    idle,
    error
}
