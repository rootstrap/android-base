package com.rootstrap.android.infraestructure.connection

/**
 * @author Amaury Ricardo Miranda 2019
 * */

interface ConnectionResponse<T> {

    fun isSuccessful(response: T? = null)
    fun onError()

}
