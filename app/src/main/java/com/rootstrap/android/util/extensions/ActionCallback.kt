package com.rootstrap.android.util.extensions

import com.google.gson.Gson
import com.rootstrap.android.bus
import com.rootstrap.android.network.models.ErrorModel
import com.rootstrap.android.util.ErrorUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class ActionCallback<T> : Callback<T> {

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            responseAction(response)
        } else {
            errorAction(response)
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        failureAction(t)
    }

    open fun responseAction(response: Response<T>) {}

    open fun errorAction(response: Response<T>) {
        try {
            val error = Gson().fromJson(response.errorBody()!!.charStream(), ErrorModel::class.java)
            bus.post(ErrorEvent(ErrorUtil.handleCustomError(error)))
        } catch (e: Exception) {
            bus.post(FailureEvent())
        }
    }

    open fun failureAction(throwable: Throwable?) {
        bus.post(FailureEvent())
    }
}

class ErrorEvent(val error: String)
class FailureEvent
