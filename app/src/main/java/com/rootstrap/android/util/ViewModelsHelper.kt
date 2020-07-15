package com.rootstrap.android.util

interface ViewModelDelegate {
    fun updateState()
    fun updateNetworkState()
}

enum class NetworkState {
    loading,
    idle,
    error
}
